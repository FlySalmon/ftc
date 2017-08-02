package com.eif.ftc.integration.risk;

import com.eif.fis.facade.dto.common.TagRules;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.risk.facade.bean.AllUserLockBean;
import com.eif.risk.facade.response.*;

import java.util.Date;

/**
 * Created by bohan on 1/20/16.
 */
public interface RiskIntService {
    void riskCheckTransIng(FundTransOrder fundTransOrder, String checkpoint, String deviceInfo, String devId, String ip);
    void riskCheckTransPost(FundTransOrder fundTransOrder, String deviceInfo, String devId, String ip);
    void checkAndAddDayStat(FundTransOrder order);
    void checkDayStat(FundTransOrder order);
    void addDayStat(FundTransOrder order, TagRules tagRule);
    void deductDayStat(FundTransOrder order, TagRules tagRule);
    QueryDayStatResp queryDayAmount(Long productId, String memberNo, int fundTransType, Date queryDate, TagRules tagRule);
    QueryDayStatByProdResp queryTodayStatByProd(FundTransOrder order);
    QueryDayStatByProdResp queryTodayStatByProd(int fundTransType, Long productId);

    void deductDayStatAsync(FundTransOrder transOrder, TagRules tagRule);
    void addDayStatAsync(FundTransOrder transOrder, TagRules tagRule);

    /**
     * 获取风控用户锁
     * @param memberNo
     * @return
     */
    AllUserLockBean getRiskUserLock(String memberNo);

    /**
     * 转让交易风控校验
     * @param transferorOrder
     * @param checkpoint
     * @param deviceInfo
     * @param devId
     * @param ip
     */
    void transferorRiskCheck(FundTransferorOrder transferorOrder, String checkpoint, 
    		String deviceInfo, String devId, String ip);
    
    /**
     * 受让交易风控校验
     * @param transfereeOrder
     * @param checkpoint
     * @param deviceInfo
     * @param devId
     * @param ip
     */
    void transfereeRiskCheck(FundTransfereeOrder transfereeOrder, String checkpoint, 
    		String deviceInfo, String devId, String ip);
    
}
