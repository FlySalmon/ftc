package com.eif.ftc.service.trans.impl;

import com.eif.fis.facade.constant.ProductCloseType;
import com.eif.fis.facade.dto.common.TagRules;
import com.eif.fis.facade.response.ftc.QueryProdTransInfoV2Resp;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.pagination.pagehelper.Page;
import com.eif.framework.pagination.pagehelper.PageHelper;
import com.eif.framework.pagination.pagehelper.PageInfo;
import com.eif.ftc.dal.dao.FundTransOrderMapper;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderExample;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransOrderStatus;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.integration.member.MemberIntService;
import com.eif.ftc.integration.ofc.OfcIntService;
import com.eif.ftc.service.trans.FundOrderExpiredService;
import com.eif.ftc.service.trans.action.FundBuyingPayActionService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.dto.MemberUpdateBean;
import com.lts.core.commons.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by bohan on 9/29/16.
 */
@Service("fundOrderExpiredService")
public class FundOrderExpiredServiceImpl implements FundOrderExpiredService {

    static Logger logger = LoggerFactory.getLogger(FundOrderExpiredServiceImpl.class);

    private int orderScanPageSize = 50;

    @Autowired
    FundTransOrderMapper fundTransOrderMapper;

    @Resource
    FisIntService fisIntService;

    @Resource
    MemberIntService memberIntService;

    @Autowired
    FundBuyingPayActionService fundBuyingPayActionService;

    @Resource
    OfcIntService ofcIntService;

    public void orderExpiredScan() {
        Date curDate = DateUtils.now();
        PageInfo<FundTransOrder> pageOrder = null;
        do {
            // 分页获取
            pageOrder = processByPage(1, orderScanPageSize, curDate, false);
        }
        while (pageOrder != null && pageOrder.getSize() > 0);
    }

    public PageInfo<FundTransOrder> processByPage(int pageNum, int pageSize, Date curDate, boolean isCount) {

        FundTransOrderExample example = new FundTransOrderExample();
        FundTransOrderExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(FundTransOrderStatus.PAYING);
        List<Integer> fundTransTypeList = new ArrayList<>();
        fundTransTypeList.add(FundTransType.PURCHASING);
        fundTransTypeList.add(FundTransType.SUBSCRIBING);
        criteria.andFundTransTypeIn(fundTransTypeList);
        criteria.andCurStatusExpiryTimeLessThanOrEqualTo(curDate);
        PageHelper.startPage(pageNum, pageSize, isCount);
        Page<FundTransOrder> batchOrderList = (Page<FundTransOrder>) fundTransOrderMapper.selectByExample(example);
        if (batchOrderList == null) {
            return null;
        }

        PageInfo<FundTransOrder> pageInfo = new PageInfo<>(batchOrderList);

        if (batchOrderList.size() > 0) {
            List<FundTransOrder> orderList = pageInfo.getList();

            // 批量获取产品信息
            Map<Long, QueryProdTransInfoV2Resp> productMap = new HashMap<>();
            for (int i = 0; i < orderList.size(); ++i) {
                FundTransOrder order = orderList.get(i);
                QueryProdTransInfoV2Resp product = productMap.get(order.getProductId());
                if (product == null) {
                    product = fisIntService.queryProdTransInfoV2(order.getProductId());
                    productMap.put(order.getProductId(), product);
                }
            }

            List<String> normalTokenList = new ArrayList<>();

            List<String> currentTokenList = new ArrayList<>();
            for (int i = 0; i < orderList.size(); ++i) {
                if (!StringUtils.isEmpty(orderList.get(i).getFrozenCode())) {
                    if(productMap.get(orderList.get(i).getProductId()).getCloseType().equals(ProductCloseType.HALF_OPEN) || productMap.get(orderList.get(i).getProductId()).getCloseType().equals(ProductCloseType.CLOSE_OPEN)) {
                        currentTokenList.add(orderList.get(i).getFrozenCode());
                    } else {
                        normalTokenList.add(orderList.get(i).getFrozenCode());
                    }
                }
            }

            List<String> normalFailTokenList = null;
            List<String> currentFailTokenList = null;


            if (normalTokenList.size() > 0) {
                // 批量解冻操作
                normalFailTokenList = fisIntService.unfreezeProdInventoryBatch(normalTokenList);
            }


            if(currentTokenList.size() > 0)
            {
                currentFailTokenList = fisIntService.unfreezeCurrentProdInventoryBatch(currentTokenList);
            }

            List<FundTransOrder> updateOrderList = new ArrayList<>();
            if (normalFailTokenList != null && normalFailTokenList.size() > 0) {

                if(currentFailTokenList != null && currentFailTokenList.size() > 0)
                {
                    normalFailTokenList.addAll(currentFailTokenList);
                }

                Set<String> failTokenSet = new HashSet<>();
                failTokenSet.addAll(normalFailTokenList);
                // 排除解冻失败的token
                for (FundTransOrder transOrder : orderList) {
                    if (!StringUtils.isEmpty(transOrder.getFrozenCode()) &&
                            failTokenSet.contains(transOrder.getFrozenCode())) {
                        continue;
                    }
                    updateOrderList.add(transOrder);
                }
            } else {
                updateOrderList.addAll(orderList);
            }

            // 更新所有的单据状态
            List<FundTransOrder> updateMemberOrderList = updateAllOrder(updateOrderList, productMap, curDate);

            List<MemberUpdateBean> memberUpdateBeanList = new ArrayList<>();
            for (FundTransOrder transOrder : updateMemberOrderList) {
                MemberUpdateBean memberUpdateBean = new MemberUpdateBean();
                memberUpdateBean.setLockStatus(-1);
                memberUpdateBean.setMemberNo(transOrder.getMemberNo());
                memberUpdateBeanList.add(memberUpdateBean);
            }

            // 优先更新为非新手,如果支付失败再返回回新手; 1代表+, -1代表减
            memberIntService.batchUpdateRookie(memberUpdateBeanList);
        }

        return pageInfo;
    }

    public List<FundTransOrder> updateAllOrder(List<FundTransOrder> batchOrderList, Map<Long, QueryProdTransInfoV2Resp> productMap, Date curDate) {
        List<FundTransOrder> retList = new ArrayList<>();

        for (int i = 0; i < batchOrderList.size(); ++i) {
            FundTransOrder order = batchOrderList.get(i);
            QueryProdTransInfoV2Resp product = productMap.get(order.getProductId());
            if (product == null) {
                product = fisIntService.queryProdTransInfoV2(order.getProductId());
                productMap.put(order.getProductId(), product);
            }

            // 更新交易单状态
            order.setStatus(FundTransOrderStatus.CLOSE_BY_EXPIRY);
            order.setFinishTime(curDate);
            order.setUpdateTime(curDate);

            List<Integer> prevStatusList = new ArrayList<>();
            prevStatusList.add(FundTransOrderStatus.PAYING);
            try {
                // 行锁并发防止
                fundBuyingPayActionService.updatePayFundTransOrderTransaction(order, prevStatusList, curDate);
            }
            catch (BusinessException be) {
                if (be.getCode().equals(CommonRspCode.DB_ERROR.getCode())) {
                    FundTransOrder newOrder = fundTransOrderMapper.selectByPrimaryKey(order.getId());
                    logger.warn("order already changed, fundTransOrderNo is " + order.getFundTransOrderNo() +
                            ", status is " + newOrder.getStatus());
                    continue;
                }
            }
            // 失败回补限额

            TagRules tagRules;
            Long groupBuyId = Long.valueOf("-1");
            String productName;
            if(product.getCloseType().equals(ProductCloseType.CLOSE))
            {
                groupBuyId = product.getNormalProdTransInfo().getGroupBuyId();
                tagRules = product.getNormalProdTransInfo().getTagRules();
                productName = product.getNormalProdTransInfo().getProductName();
            }
            else
            {
                tagRules = product.getCurrentProdTransInfo().getTagRules();
                productName = product.getCurrentProdTransInfo().getProductName();
            }
            fundBuyingPayActionService.addDayQuotaCompensable(order, tagRules);

            // 如果是团购产品, 失败解冻团购库存
            if (groupBuyId != -1) {
                fundBuyingPayActionService.freezeGrouponInventoryCompensable(order);
            }

            ofcIntService.updateBusinessOrderItemStatusAsync(order, order.getStatus(), productName);

            retList.add(order);
        }

        return retList;
    }

    public int getOrderScanPageSize() {
        return orderScanPageSize;
    }

    public void setOrderScanPageSize(int orderScanPageSize) {
        this.orderScanPageSize = orderScanPageSize;
    }
}
