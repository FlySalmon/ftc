package com.eif.ftc.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eif.ftc.dal.model.MemberFundAccount;

import tk.mybatis.mapper.common.Mapper;

public interface MemberFundAccountMapper extends Mapper<MemberFundAccount> {
    MemberFundAccount selectByAccountNo(@Param("account_uuid") String account_uuid);

    MemberFundAccount selectByMemberNo(@Param("member_no") String member_no);

    MemberFundAccount selectByFTCOrderNo(@Param("ftc_order_no") String ftc_order_no);

    MemberFundAccount selectByTransactionAccontAndTaAccount(@Param("transactionAccount") String transactionAccount, @Param("taAccount") String taAccount);
    
    /**
     * 批量更新记录
     * @param memberFundAccountList
     * @return
     */
    int batchUpdateFundAccountByOrderNo(List<MemberFundAccount> memberFundAccountList);
    
    /**
     * 根据会员号获取基金账户信息
     * @param memberNos
     * @return
     */
    List<MemberFundAccount> selectByMemberNos(List<String> memberNos);
    
}