package com.eif.ftc.service.transfer.action.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.ftc.dal.dao.FundTransfereeOrderMapper;
import com.eif.ftc.dal.dao.FundTransfereeOrderStatusInfoMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderMapper;
import com.eif.ftc.dal.dao.FundTransferorOrderStatusInfoMapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransfereeOrderExample;
import com.eif.ftc.dal.model.FundTransfereeOrderStatusInfo;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.dal.model.FundTransferorOrderExample;
import com.eif.ftc.dal.model.FundTransferorOrderStatusInfo;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransfereeOrderStatus;
import com.eif.ftc.facade.fund.transfer.enumeration.FundTransferorOrderStatus;
import com.eif.ftc.service.transfer.action.FundTransferOrderActionService;
import com.eif.ftc.util.exception.BusinessException;
import com.lts.core.commons.utils.DateUtils;

import ma.glasnost.orika.MapperFacade;
import tk.mybatis.mapper.util.StringUtil;

@Service("fundTransferOrderActionService")
public class FundTransferOrderActionServiceImpl implements FundTransferOrderActionService {

	private static Logger logger = LoggerFactory.getLogger(FundTransferOrderActionServiceImpl.class);

    @Autowired
    private FundTransfereeOrderMapper fundTransfereeOrderMapper;
    
    @Autowired
    private FundTransfereeOrderStatusInfoMapper fundTransfereeOrderStatusInfoMapper;

    @Autowired
    private FundTransferorOrderMapper fundTransferorOrderMapper;
    
    @Autowired
    private FundTransferorOrderStatusInfoMapper fundTransferorOrderStatusInfoMapper;

    @Autowired
    private MapperFacade mapperFacade;

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void insertFundTransferorOrder(FundTransferorOrder transferorOrder) {
		// 插入订单
		fundTransferorOrderMapper.insertSelective(transferorOrder);

		// 插入订单迁移状态
		insertFundTransferorOrderStatus(transferorOrder);
	}

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void insertFundTransfereeOrder(FundTransfereeOrder transfereeOrder) {
		// 插入订单
		fundTransfereeOrderMapper.insertSelective(transfereeOrder);

		// 插入订单迁移状态
		insertFundTransfereeOrderStatus(transfereeOrder);
	}

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateFundTransfereeOrder(FundTransfereeOrder transfereeOrder, int targetStatus) {
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransfereeOrderNoEqualTo(transfereeOrder.getFundTransfereeOrderNo());
		criteria.andStatusEqualTo(transfereeOrder.getStatus());
		
		FundTransfereeOrder updateOrder = new FundTransfereeOrder();
		mapperFacade.map(transfereeOrder, updateOrder);
		updateOrder.setStatus(targetStatus);
		updateOrder.setUpdateTime(DateUtils.now());
		if (targetStatus == FundTransfereeOrderStatus.TRANS_SUC) {
			updateOrder.setFinishTime(updateOrder.getUpdateTime());
		}
		int count = fundTransfereeOrderMapper.updateByExampleSelective(updateOrder, example);
		if (count > 0) {
			if (transfereeOrder.getStatus() != targetStatus) {
				// 插入订单迁移状态
				insertFundTransfereeOrderStatus(updateOrder);
				// 更新订单状态
				transfereeOrder.setStatus(targetStatus);
				transfereeOrder.setUpdateTime(updateOrder.getUpdateTime());
			}
		} else {
			logger.error("updateFundTransfereeOrder failed for not find transfereeOrder, orderNo: " + 
					transfereeOrder.getFundTransfereeOrderNo() + ", status: " + transfereeOrder.getStatus());
            throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
		}
	}

	@Override
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateFundTransferorOrder(FundTransferorOrder transferorOrder, int targetStatus) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(transferorOrder.getFundTransferorOrderNo());
		criteria.andStatusEqualTo(transferorOrder.getStatus());
		
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		mapperFacade.map(transferorOrder, updateOrder);
		updateOrder.setStatus(targetStatus);
		updateOrder.setUpdateTime(DateUtils.now());
		if (targetStatus == FundTransferorOrderStatus.TRANSFER_SUC) {
			updateOrder.setFinishTime(updateOrder.getUpdateTime());
		}
		int count = fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		if (count > 0) {
			if (transferorOrder.getStatus() != targetStatus) {
				// 插入订单迁移状态
				insertFundTransferorOrderStatus(updateOrder);
				// 更新订单状态
				transferorOrder.setStatus(targetStatus);
				transferorOrder.setUpdateTime(updateOrder.getUpdateTime());
			}
		} else {
			logger.error("updateFundTransferorOrder failed for not find transferorOrder, orderNo: " + 
					transferorOrder.getFundTransferorOrderNo() + ", status: " + transferorOrder.getStatus());
			throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
		}
	}

	@Override
	public void updateFundTransfereeOrder(FundTransfereeOrder transfereeOrder) {
		FundTransfereeOrderExample example = new FundTransfereeOrderExample();
		FundTransfereeOrderExample.Criteria criteria = example.createCriteria();
		if (transfereeOrder.getId() != null) {
			criteria.andIdEqualTo(transfereeOrder.getId());
		} else if (!StringUtil.isEmpty(transfereeOrder.getBusinessOrderItemNo())) {
			criteria.andBusinessOrderItemNoEqualTo(transfereeOrder.getBusinessOrderItemNo());
		} else if (!StringUtil.isEmpty(transfereeOrder.getFundTransfereeOrderNo())) {
			criteria.andFundTransfereeOrderNoEqualTo(transfereeOrder.getFundTransfereeOrderNo());
		}
		
		int count = fundTransfereeOrderMapper.updateByExampleSelective(transfereeOrder, example);
		if (count < 1) {
			logger.error("updateFundTransfereeOrder failed, orderInfo: " + JSON.toJSONString(transfereeOrder));
            throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
		}
	}

	@Override
	public void updateFundTransferorOrder(FundTransferorOrder transferorOrder) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		if (transferorOrder.getId() != null) {
			criteria.andIdEqualTo(transferorOrder.getId());
		} else if (!StringUtil.isEmpty(transferorOrder.getBusinessOrderItemNo())) {
			criteria.andBusinessOrderItemNoEqualTo(transferorOrder.getBusinessOrderItemNo());
		} else if (!StringUtil.isEmpty(transferorOrder.getFundTransferorOrderNo())) {
			criteria.andFundTransferorOrderNoEqualTo(transferorOrder.getFundTransferorOrderNo());
		}
		
		int count = fundTransferorOrderMapper.updateByExampleSelective(transferorOrder, example);
		if (count < 1) {
			logger.error("updateFundTransferorOrder failed, orderInfo: " + JSON.toJSONString(transferorOrder));
			throw new BusinessException(CommonRspCode.DB_ERROR.getMessage(), CommonRspCode.DB_ERROR.getCode());
		}
	}

	@Override
	public void insertFundTransfereeOrderStatus(FundTransfereeOrder transfereeOrder) {
		FundTransfereeOrderStatusInfo statusInfo = new FundTransfereeOrderStatusInfo();
		statusInfo.setOrderId(transfereeOrder.getId());
		statusInfo.setStatus(transfereeOrder.getStatus());
		statusInfo.setUpdateTime(transfereeOrder.getUpdateTime());
		fundTransfereeOrderStatusInfoMapper.insertSelective(statusInfo);
	}

	@Override
	public void insertFundTransferorOrderStatus(FundTransferorOrder transferorOrder) {
		FundTransferorOrderStatusInfo statusInfo = new FundTransferorOrderStatusInfo();
		statusInfo.setOrderId(transferorOrder.getId());
		statusInfo.setStatus(transferorOrder.getStatus());
		statusInfo.setUpdateTime(transferorOrder.getUpdateTime());
		fundTransferorOrderStatusInfoMapper.insertSelective(statusInfo);
	}

	@Override
	public void checkTransfereeOrderStatus(FundTransfereeOrder transfereeOrder, int expectStatus) {
		if (transfereeOrder == null || transfereeOrder.getStatus() != expectStatus) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFEREE_ORDER_NOT_EXIST.getCode());
		}
	}

	@Override
	public void checkTransferorOrderStatus(FundTransferorOrder transferorOrder, int expectStatus) {
		if (transferorOrder == null || transferorOrder.getStatus() == FundTransferorOrderStatus.TRANSFER_FAILED) {
			throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(),
					FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
		}
		if (transferorOrder.getStatus() == expectStatus) {
			return;
		}
		
		String code = "", msg = "";
		switch (transferorOrder.getStatus()) {
		case FundTransferorOrderStatus.CANCEL:
			code = FTCRespCode.ERR_FUND_TRANSFEROR_CANCEL_BY_USER.getCode();
			msg = FTCRespCode.ERR_FUND_TRANSFEROR_CANCEL_BY_USER.getMessage();
			break;
		case FundTransferorOrderStatus.CLOSE_BY_EXPIRY:
			code = FTCRespCode.ERR_FUND_TRANSFEROR_CLOSE_BY_EXPIRED.getCode();
			msg = FTCRespCode.ERR_FUND_TRANSFEROR_CLOSE_BY_EXPIRED.getMessage();
			break;
		case FundTransferorOrderStatus.CANCEL_BY_RISK:
			code = FTCRespCode.ERR_FUND_TRANSFEROR_CANCEL_BY_RISK.getCode();
			msg = FTCRespCode.ERR_FUND_TRANSFEROR_CANCEL_BY_RISK.getMessage();
			break;
		case FundTransferorOrderStatus.TRANSFER_SUC:
			code = FTCRespCode.ERR_FUND_TRANSFEROR_DEAL_SUCC.getCode();
			msg = FTCRespCode.ERR_FUND_TRANSFEROR_DEAL_SUCC.getMessage();
			break;
		case FundTransferorOrderStatus.TRANSFERING:
		case FundTransferorOrderStatus.NEED_TRANSFER:
			code = FTCRespCode.ERR_FUND_TRANSFEROR_PROCESSING.getCode();
			msg = FTCRespCode.ERR_FUND_TRANSFEROR_PROCESSING.getMessage();
			break;
		default:
			code = CommonRspCode.SYS_ERROR.getCode();
			msg = CommonRspCode.SYS_ERROR.getMessage();
			break;
		}
		
		throw new BusinessException(msg, code);
	}

	@Override
	public void updateFundTransferorOrderMktProductId(String transferorOrderNo, Long mktProductId) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(transferorOrderNo);
		criteria.andMktProductIdEqualTo(TransferApplyActionServiceImpl.DEFAULT_MKT_PRODUCT_ID);
		
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		updateOrder.setMktProductId(mktProductId);
		int count = fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		if (count == 0) {
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(transferorOrderNo);
			count = fundTransferorOrderMapper.selectCount(queryOrder);
			if (count == 0) {
				logger.error("update mktProductId, cannot find FundTransferorOrder by orderNo: " + transferorOrderNo);
				throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(),
						FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			}
		}
	}

	@Override
	public void updateFundTransferorOrderAgreementNo(String transferorOrderNo, String agreementNo) {
		FundTransferorOrderExample example = new FundTransferorOrderExample();
		FundTransferorOrderExample.Criteria criteria = example.createCriteria();
		criteria.andFundTransferorOrderNoEqualTo(transferorOrderNo);
		criteria.andTransferAgreementNoEqualTo("");
		
		FundTransferorOrder updateOrder = new FundTransferorOrder();
		updateOrder.setTransferAgreementNo(agreementNo);
		int count = fundTransferorOrderMapper.updateByExampleSelective(updateOrder, example);
		if (count == 0) {
			FundTransferorOrder queryOrder = new FundTransferorOrder();
			queryOrder.setFundTransferorOrderNo(transferorOrderNo);
			count = fundTransferorOrderMapper.selectCount(queryOrder);
			if (count == 0) {
				logger.error("update agreementNo, cannot find FundTransferorOrder by orderNo: " + transferorOrderNo);
				throw new BusinessException(FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getMessage(),
						FTCRespCode.ERR_FUND_TRANSFEROR_ORDER_NOT_EXIST.getCode());
			}
		}
	}

}
