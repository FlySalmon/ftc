package com.eif.ftc.integration.impl.contract;

import com.eif.contract.facade.biz.ftc.ContractFtcFacade;
import com.eif.contract.facade.dto.ftc.ContractSecMarketTransferee;
import com.eif.contract.facade.dto.ftc.ContractSecMarketTransferor;
import com.eif.contract.facade.request.ftc.AssignorTransfereeContractReq;
import com.eif.contract.facade.request.ftc.AssignorTransferorContractReq;
import com.eif.contract.facade.request.ftc.BatchContractAddtionalReq;
import com.eif.contract.facade.request.ftc.FinishContractReq;
import com.eif.contract.facade.request.ftc.InsertContractReq;
import com.eif.contract.facade.response.ftc.FinishContractResp;
import com.eif.contract.facade.response.ftc.QueryBatchAdditionalBybatchContractSnResp;
import com.eif.fis.facade.dto.ftc.ProdInfo;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.mq.common.MessageWrapper;
import com.eif.ftc.dal.model.FundTransfereeOrder;
import com.eif.ftc.dal.model.FundTransferorOrder;
import com.eif.ftc.facade.mq.dto.topic.MQTopic;
import com.eif.ftc.integration.contract.ContractIntService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.member.facade.pkg.dto.responseDTO.MemberIdentityBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by bohan on 1/11/16.
 */
@Service("contractIntService")
public class ContractIntServiceImpl implements ContractIntService {
    private static Logger logger = LoggerFactory.getLogger(ContractIntServiceImpl.class);

    @Resource(name = "contractFtcFacade")
    ContractFtcFacade contractFtcFacade;

    @Resource(name="defaultRMQProducer")
    private DefaultRMQProducer defaultRMQProducer;
    
    public QueryBatchAdditionalBybatchContractSnResp queryBatchAddtionalByBatchContractSn(List<String> batchContract) {
        BatchContractAddtionalReq contractReq = new BatchContractAddtionalReq();
        contractReq.setBatchContract(batchContract);
        QueryBatchAdditionalBybatchContractSnResp resp =
                contractFtcFacade.queryBatchAddtionalByBatchContractSn(contractReq);
        if (!resp.isSuccess()) {
            logger.error("query contract coupon info failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }

        return resp;
    }

	@Override
	public void signContractAsync(InsertContractReq contractRequest) {
		MessageWrapper msg = new MessageWrapper(MQTopic.TOPIC_NOTIFY_SIGN_CONTRACT,// topic
                null,// tag
                contractRequest.getOrderId(),// key
                contractRequest);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Throwable e) {
            logger.error("send sign contract message err, exception is", e);
        }
	}
	
	@Override
	public void terminateContract(String contractNo, String agreementNo) {
		FinishContractReq request = new FinishContractReq();
		request.setContractSn(contractNo);
		request.setRemark(agreementNo);
		
		FinishContractResp resp = contractFtcFacade.finishContract(request);
		if (!resp.isSuccess()) {
            logger.error("terminate contract failed, errmsg: " + resp.getMsg());
            throw new BusinessException(resp.getMsg(), resp.getRespCode());
        }
	}

	@Override
	public void signTransferorAgreementAsync(FundTransferorOrder transferorOrder, ProdInfo product,
			MemberIdentityBean member) {
		AssignorTransferorContractReq request = new AssignorTransferorContractReq();
		ContractSecMarketTransferor agreement = new ContractSecMarketTransferor();
		agreement.setProductId(product.getProductId());
		agreement.setCloseType(product.getCloseType());
		agreement.setTransferorIdcardType(member.getCertificationType());
		agreement.setTransferorIdcard(member.getCertificationNo());
		agreement.setTransferorName(member.getRealName());
		agreement.setTransferorAccount(member.getLoginAlias());
		agreement.setTransferorPhone(member.getVerifiedMobile());
		agreement.setTransferorNum(transferorOrder.getFundTransferorOrderNo());
		agreement.setTranferorTime(transferorOrder.getCreateTime());
		agreement.setTransferorId(transferorOrder.getMemberNo());
		request.setContractSecMarketTransferor(agreement);

		MessageWrapper msg = new MessageWrapper(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT,// topic
                null,// tag
                transferorOrder.getFundTransferorOrderNo(),// key
                request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Throwable e) {
            logger.error("send sign transferor agreement message err, exception is", e);
        }
	}

	@Override
	public void signTransfereeAgreementAsync(FundTransfereeOrder transfereeOrder, MemberIdentityBean member) {
		AssignorTransfereeContractReq request = new AssignorTransfereeContractReq();
		ContractSecMarketTransferee agreement = new ContractSecMarketTransferee();
		agreement.setProductId(transfereeOrder.getMktProductId());
		agreement.setTransfereeIdcardType(member.getCertificationType());
		agreement.setTransfereeIdcard(member.getCertificationNo());
		agreement.setTransfereeName(member.getRealName());
		agreement.setTransfereeAccount(member.getLoginAlias());
		agreement.setTransfereePhone(member.getVerifiedMobile());
		agreement.setTransferNo(transfereeOrder.getTransferAgreementNo());
		agreement.setTransfereeNum(transfereeOrder.getFundTransfereeOrderNo());
		agreement.setTransfereeId(transfereeOrder.getMemberNo());
		request.setContractSecMarketTransferee(agreement);
		
		MessageWrapper msg = new MessageWrapper(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT,// topic
                null,// tag
                transfereeOrder.getFundTransfereeOrderNo(),// key
                request);
        try {
            defaultRMQProducer.sendWithRetry(msg);
        } catch (Throwable e) {
            logger.error("send sign transferee agreement message err, exception is", e);
        }
	}

}

