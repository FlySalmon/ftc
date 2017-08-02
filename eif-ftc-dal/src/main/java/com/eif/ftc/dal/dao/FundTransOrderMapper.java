package com.eif.ftc.dal.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.eif.ftc.dal.bean.MemberInvestmentBean;
import com.eif.ftc.dal.model.FundTransOrder;
import com.eif.ftc.dal.model.FundTransOrderDiversionData;


public interface FundTransOrderMapper extends Mapper<FundTransOrder> {

	/**
	 * 查询交易单
	 * @param typeList
	 * @param statusList
	 * @param startDate
	 * @param endDate
	 * @return
	 */
    List<FundTransOrder> queryTATransOrder(@Param("typeList") List<Integer> typeList, 
    		@Param("statusList") List<Integer> statusList, 
    		@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 批量插入交易单
     * @param orderList
     * @return
     */
    int batchInsertOrder(List<FundTransOrder> orderList);
    
    /**
     * 更新交易单信息
     * @param orderList
     * @return
     */
    int batchUpdateOrder(List<FundTransOrder> orderList);

    /**
     * 批量更新合同信息
     * @param orderList
     * @return
     */
    int batchUpdateContract(List<FundTransOrder> orderList);
    
    /**
     * 根据交易核心流水号查找订单
     * @param transNoList
     * @return
     */
    List<FundTransOrder> selectByTranscoreTransNo(List<String> transNoList);

    /**
     * 根据订单号查找订单
     * @param transOrderNoList
     * @return
     */
    List<FundTransOrder> selectByFundTransOrderNo(List<String> transOrderNoList);
    
    /**
     * 获取产品订单
     * @param productId
     * @param status
     * @return
     */
    List<FundTransOrder> queryOrderByProductAndStatus(@Param("productId") Long productId, @Param("status")Integer status);
    
    /**
     * 获取产品订单
     * @param productId
     * @return
     */
    List<FundTransOrder> queryProductOrder(@Param("productId") Long productId);
    
    /**
     * 统计订单数量
     * @param startDate
     * @param endDate
     * @param transTypeList
     * @param status
     * @return
     */
    int selectOrderCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
    		@Param("transTypeList") List<Integer> transTypeList, @Param("status") Integer status);
    
    //根据ta单号查找交易单信息
    List<FundTransOrder> selectByTaSerialNo(List<String> taSerialNoList);

    //根据产品和交易账号查找订单信息
    List<FundTransOrder> queryOrderByProductAndTransAccount(@Param("productId") Long productId, @Param("transAccList") List<String> transAccList);

    //根据产品和会员号查找订单信息
    List<FundTransOrder> queryOrderByProductAndMemberNo(@Param("productId") Long productId, @Param("memberNoList") List<String> memberNoList);

    int batchUpdateForExpiredTimer(List<FundTransOrder> orderList);

    /**
     * 获取导流数据
     * @param typeList
     * @param statusList
     * @param startDate
     * @param endDate
     * @return
     */
    List<FundTransOrderDiversionData> queryTransOrderDiversionData(
    		@Param("typeList") List<Integer> typeList, 
    		@Param("statusList") List<Integer> statusList, 
    		@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 获取大额投资用户
     * @param memberNos
     * @param largeInvestorThreshold
     * @param transTypeList
     * @return
     */
    List<String> queryLargeInvestmentClinets(@Param("memberNos") List<String> memberNos, 
    		@Param("largeInvestorThreshold") BigDecimal largeInvestorThreshold,
    		@Param("transTypeList") List<Integer> transTypeList);
    
    /**
     * 统计好友投资金额
     * @param transType
     * @param startDt
     * @param endDt
     * @param memberNos
     * @param productIds
     * @return
     */
    List<MemberInvestmentBean> queryFriendsInvestment(@Param("transType") Integer transType,
    		@Param("startDt") Date startDt, @Param("endDt") Date endDt, 
    		@Param("memberNos") List<String> memberNos, 
    		@Param("productIds") List<Long> productIds);

    /**
     * 获取会员投资产品IDs
     * @param transType
     * @param startDt
     * @param endDt
     * @param memberNos
     * @return
     */
    List<Long> queryMemberProductIds(@Param("transType") Integer transType,
    		@Param("startDt") Date startDt, @Param("endDt") Date endDt, 
    		@Param("memberNos") List<String> memberNos);
    
    /**
     * 获取用户申购单中的产品信息
     * @param transType
     * @param memberNos
     * @return
     */
    List<FundTransOrder> queryTATransOrderFundInfoByTypeAndMembers(@Param("transType") Integer transType, 
    		@Param("memberNos") List<String> memberNos);

    /**
     * 根据产品和会员号查找订单中的会员对应活期信息
     * @param productId
     * @param memberNoList
     * @return
     */
    List<FundTransOrder> queryMemberCurrentFundInfo(@Param("productId") Long productId, 
    		@Param("memberNoList") List<String> memberNoList);

}