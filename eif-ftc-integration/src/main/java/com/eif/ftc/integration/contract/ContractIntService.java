package com.eif.ftc.integration.contract;

import com.eif.contract.facade.request.ftc.InsertContractReq;
import com.eif.contract.facade.response.ftc.QueryBatchAdditionalBybatchContractSnResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;

import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
public interface ContractIntService {
    QueryBatchAdditionalBybatchContractSnResp queryBatchAddtionalByBatchContractSn(List<String> batchContract);
    
    /**
     * 签订合同
     * @param contractRequest
     */
    void signContractAsync(InsertContractReq contractRequest);
    
    /**
     * 终止合同
     * @param contractNo
     * @param agreementNo
     */
    void terminateContract(String contractNo, String agreementNo);
    
    /**
     * 异步签订转让（转让者）协议
     * @param transferorOrder
     * @param product
     * @param member
     */
    void signTransferorAgreementAsync(FundTransferorOrder transferorOrder, ProdInfo product, MemberIdentityBean member);
    
    /**
     * 异步签订转让（受让者）协议
     * @param transfereeOrder
     * @param member
     */
    void signTransfereeAgreementAsync(FundTransfereeOrder transfereeOrder, MemberIdentityBean member);
    
}
