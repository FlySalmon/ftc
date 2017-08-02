package com.eif.ftc.biz.mq.listener;

import java.util.List;

import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.service.transfer.FundTransfereeService;
import com.eif.ftc.service.transfer.FundTransferorService;
import com.eif.inspection.facade.mq.MQInsHandleDataInfo;
import com.eif.risk.facade.bean.UserBlockingBean;
import com.eif.risk.facade.constant.MQTopicName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.eif.contract.facade.response.ftc.AssignorTransfereeContractResp;
import com.eif.contract.facade.response.ftc.AssignorTransferorContractResp;
import com.eif.contract.facade.response.ftc.InsertContractResp;
import com.eif.fis.facade.constant.MarketLevel;
import com.eif.fis.facade.constant.MqTopic;
import com.eif.fis.facade.response.ftc.CreateSecMarketProdResp;
import com.eif.framework.common.utils.constant.BizSysCode;
import com.eif.framework.mq.common.MessageExtWrapper;
import com.eif.framework.mq.listener.MessageListenerConcurrentlyEx;
import com.eif.ftc.facade.fund.trans.enumeration.BusinessTransType;
import com.eif.ftc.facade.fund.trans.enumeration.FundTransType;
import com.eif.ftc.facade.fund.trans.enumeration.SignContractBizCode;
import com.eif.ftc.facade.mq.dto.topic.MQTopic;
import com.eif.setting.facade.constant.MQTopicConst;
import com.eif.setting.facade.request.CusTransResultRequest;
import com.eif.setting.facade.response.BatchCusTransResponse;
import com.eif.transcore.facade.mq.MQTransConstants;
import com.eif.transcore.facade.mq.MQTransInfoBean;
import com.eif.transcore.facade.response.BatchReportTransEventResponse;
import com.eif.transcore.facade.response.CreateTransResponse;

public class FTCMessageListener extends MessageListenerConcurrentlyEx {

    private static final Logger logger = LoggerFactory.getLogger(FTCMessageListener.class);

	@Autowired
	FundMQConsumerService fundMQConsumerService;
	
	@Autowired
	FundTransferorService fundTransferorService;

	@Autowired
	FundTransfereeService fundTransfereeService;

    @Override
    public void consume(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for(MessageExt messageExt : list) {
        	Object obj = getObjectInstance((MessageExtWrapper) messageExt);

			// 处理交易核心消息
        	if (messageExt.getTopic().equals(MQTransConstants.TOPIC_CREATE_TRANS_RESPONSE)) {//交易单创建结果
        		CreateTransResponse message = (CreateTransResponse) obj;
        		if (message == null || !message.getBizSysCode().equals(BizSysCode.FTC_SYSTEM)) {
        			return;
        		}
        		
                int transType = message.getBizTransType();
                switch (transType) {
                    case FundTransType.PURCHASING:
                    case FundTransType.SUBSCRIBING:
                    case FundTransType.REDEEMING:
                    	fundMQConsumerService.consumeTransCoreCreateMessage(message);
                    	break;
                    case FundTransType.TRANSFEREE://受让支付、受让转账 交易单创建结果
                    	fundTransfereeService.handleCreateTranscoreMessage(message);
                    	break;
                    default:
                        break;
                }
        	} else if(messageExt.getTopic().equals(MQTransConstants.TOPIC_TRANS_STATUS)) {//交易状态
				MQTransInfoBean message = (MQTransInfoBean) obj;
				if (message == null || !message.getBizSysCode().equals(BizSysCode.FTC_SYSTEM)) {
        			return;
        		}

                int transType = message.getBizTransType();
                switch (transType) {
                    case FundTransType.PURCHASING:
                    case FundTransType.SUBSCRIBING:
                    case FundTransType.REDEEMING:
                    case FundTransType.IMMEDIATELY_REDEEMING:
						fundMQConsumerService.consumeTransCoreStatusMessage(message);
                        break;
                    case FundTransType.TRANSFEREE://受让支付、受让转账 状态更新结果
                    	fundTransfereeService.handleTranscoreStatusMessage(message);
                    	break;
                    default:
                        break;
                }
            }
			// 处理活期赎回, 定期退款消息
            else if (messageExt.getTopic().equals(MQTopic.TOPIC_SETTING_TRANS_UNFIX_RESULT)			//活期赎回结果
    			|| messageExt.getTopic().equals(MQTopicConst.TOPIC_SETTING_TRANS_REFUND_RESULE)) {	//退款结果
            	CusTransResultRequest message = (CusTransResultRequest) obj;
            	if (message == null) {
					return;
				}

				fundMQConsumerService.consumeSettingsMessage(message);
            }
			// 处理退款出款回调
            else if (messageExt.getTopic().equals(MQTopicConst.TOPIC_SETTING_CUS_TRANS_COLLECTOR_RESPONSE)) {
            	BatchCusTransResponse message = (BatchCusTransResponse) obj;
            	if (message == null) {
					return;
				}
				fundMQConsumerService.consumeSettingCreateOrderResponse(message.getCusTransResponseDtoList());
            } else if (messageExt.getTopic().equals(MQTopicName.USER_BLOCKING_NOTIFY)) {////风控黑名单、锁定
            	UserBlockingBean message = (UserBlockingBean) obj;
            	if (message == null) {
					return;
				}
            	
            	fundTransferorService.handleRiskLockUserMessage(message);
            } else if (messageExt.getTopic().equals(MQTopic.TOPIC_NOTIFY_SIGN_CONTRACT_RESULT)) {//签合同结果
            	InsertContractResp message = (InsertContractResp) obj;
            	if ((message.getBusinessCode() == SignContractBizCode.TRANSFEREE) ||
            			message.getMarketLevel().equals(MarketLevel.SECOND)) {// 二级市场
            		fundTransfereeService.handleSignContractResult(message);
            	}
	        } else if (messageExt.getTopic().equals(MQTransConstants.TOPIC_TRANS_BATCH_REPORT_EVENT_RESPONSE)) {//
	        	BatchReportTransEventResponse message = (BatchReportTransEventResponse) obj;
            	if (message.getBusiType() == BusinessTransType.TRANSFER_CONFIRM) {// 二级市场转账确认
            		fundTransfereeService.handleTranscoreReportEventResult(message);
            	}
	        } else if (messageExt.getTopic().equals(MqTopic.CREATE_SEC_MARKET_PRODUCT_RESULT)) {//创建二级市场产品结果
	        	CreateSecMarketProdResp message = (CreateSecMarketProdResp) obj;
	        	fundTransferorService.handleCreateMktProductResult(message);
	        } else if (messageExt.getTopic().equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT_RESULT)) {//签订二级市场转让者转让协议结果
	        	AssignorTransferorContractResp message = (AssignorTransferorContractResp) obj;
	        	fundTransferorService.handleSignTransferorAgreementResult(message);
	        } else if (messageExt.getTopic().equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT_RESULT)) {//签订二级市场受让者转让协议结果
	        	AssignorTransfereeContractResp message = (AssignorTransfereeContractResp) obj;
	        	fundTransfereeService.handleSignTransferAgreementResult(message);
			} else if (messageExt.getTopic().equals(MQTopic.TOPIC_FTC_FIS_INVENTORY_DIFF_HANDLER)) {//ftc对接inspection系统，接收库存差异对账结果
				MQInsHandleDataInfo message = (MQInsHandleDataInfo) obj;

			}
		}

    }
    
    /**
	 * 获取对象实例
	 * @param messageExtWrapper
	 * @return
	 */
    public static Object getObjectInstance(MessageExtWrapper messageExtWrapper) {
		String topic = messageExtWrapper.getTopic();
		Object messageObject = messageExtWrapper.getMessageObject();
		
		Object obj = null;
		try {
			if (messageObject != null) {
				if (topic.equals(MQTransConstants.TOPIC_CREATE_TRANS_RESPONSE)) {//交易单创建结果
					if (messageObject instanceof CreateTransResponse) {
						obj = (CreateTransResponse) messageObject;
					}
	        	} else if (topic.equals(MQTransConstants.TOPIC_TRANS_STATUS)) {//交易单结果
	        		if (messageObject instanceof MQTransInfoBean) {
	        			obj = (MQTransInfoBean) messageObject;
	        		}
	        	} else if (topic.equals(MQTopicConst.TOPIC_SETTING_CUS_TRANS_COLLECTOR_RESPONSE)) {//创建结算单结果
	        		if (messageObject instanceof BatchCusTransResponse) {
	        			obj = (BatchCusTransResponse) messageObject;
	        		}
	        	} else if (topic.equals(MQTopic.TOPIC_SETTING_TRANS_UNFIX_RESULT)			//活期赎回结果
	        			|| topic.equals(MQTopicConst.TOPIC_SETTING_TRANS_REFUND_RESULE)) {	//退款结果
	        		if (messageObject instanceof CusTransResultRequest) {
	        			obj = (CusTransResultRequest) messageObject;
	        		}
	        	} else if (topic.equals(MQTopicName.USER_BLOCKING_NOTIFY)) {	//风控黑名单、锁定
	        		if (messageObject instanceof UserBlockingBean) {
	        			obj = (UserBlockingBean) messageObject;
	        		}
	        	} else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_CONTRACT_RESULT)) {//签合同结果
	        		if (messageObject instanceof InsertContractResp) {
	        			obj = (InsertContractResp) messageObject;
	        		}
		        } else if (topic.equals(MQTransConstants.TOPIC_TRANS_BATCH_REPORT_EVENT_RESPONSE)) {//交易状态通知结果
		        	if (messageObject instanceof BatchReportTransEventResponse) {
		        		obj = (BatchReportTransEventResponse) messageObject;
		        	}
		        } else if (topic.equals(MqTopic.CREATE_SEC_MARKET_PRODUCT_RESULT)) {//创建二级市场产品结果
		        	if (messageObject instanceof CreateSecMarketProdResp) {
		        		obj = (CreateSecMarketProdResp) messageObject;
		        	}
		        } else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT_RESULT)) {//签订二级市场转让者转让协议结果
		        	if (messageObject instanceof AssignorTransferorContractResp) {
		        		obj = (AssignorTransferorContractResp) messageObject;
		        	}
		        } else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT_RESULT)) {//签订二级市场受让者转让协议结果
		        	if (messageObject instanceof AssignorTransfereeContractResp) {
		        		obj = (AssignorTransfereeContractResp) messageObject;
		        	}
				} else if (topic.equals(MQTopic.TOPIC_FTC_FIS_INVENTORY_DIFF_HANDLER)) {//ftc对接inspection系统，接收库存差异对账结果
					if (messageObject instanceof MQInsHandleDataInfo) {
						obj = (MQInsHandleDataInfo) messageObject;
					}
				}
			}
			if (obj == null) {
				if (topic.equals(MQTransConstants.TOPIC_CREATE_TRANS_RESPONSE)) {//交易单创建结果
					obj = JSON.parseObject(messageExtWrapper.getBody(), CreateTransResponse.class);
	        	} else if (topic.equals(MQTransConstants.TOPIC_TRANS_STATUS)) {//交易单结果
	        		obj = JSON.parseObject(messageExtWrapper.getBody(), MQTransInfoBean.class);
	        	} else if (topic.equals(MQTopicConst.TOPIC_SETTING_CUS_TRANS_COLLECTOR_RESPONSE)) {//创建结算单结果
	        		obj = JSON.parseObject(messageExtWrapper.getBody(), BatchCusTransResponse.class);
	        	} else if (topic.equals(MQTopic.TOPIC_SETTING_TRANS_UNFIX_RESULT)			//活期赎回结果
	        			|| topic.equals(MQTopicConst.TOPIC_SETTING_TRANS_REFUND_RESULE)) {	//退款结果
	        		obj = JSON.parseObject(messageExtWrapper.getBody(), CusTransResultRequest.class);
	        	} else if (topic.equals(MQTopicName.USER_BLOCKING_NOTIFY)) {	//风控黑名单、锁定
	        		obj = JSON.parseObject(messageExtWrapper.getBody(), UserBlockingBean.class);
	        	} else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_CONTRACT_RESULT)) {//签合同结果
		        	obj = JSON.parseObject(messageExtWrapper.getBody(), InsertContractResp.class);
		        } else if (topic.equals(MQTransConstants.TOPIC_TRANS_BATCH_REPORT_EVENT_RESPONSE)) {//交易状态通知结果
		        	obj = JSON.parseObject(messageExtWrapper.getBody(), BatchReportTransEventResponse.class);
		        } else if (topic.equals(MqTopic.CREATE_SEC_MARKET_PRODUCT_RESULT)) {//创建二级市场产品结果
		        	obj = JSON.parseObject(messageExtWrapper.getBody(), CreateSecMarketProdResp.class);
		        } else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEROR_AGREEMENT_RESULT)) {//签订二级市场转让者转让协议结果
		        	obj = JSON.parseObject(messageExtWrapper.getBody(), AssignorTransferorContractResp.class);
		        } else if (topic.equals(MQTopic.TOPIC_NOTIFY_SIGN_TRANSFEREE_AGREEMENT_RESULT)) {//签订二级市场受让者转让协议结果
		        	obj = JSON.parseObject(messageExtWrapper.getBody(), AssignorTransfereeContractResp.class);
				} else if (topic.equals(MQTopic.TOPIC_FTC_FIS_INVENTORY_DIFF_HANDLER)) {////ftc对接inspection系统，接收库存差异对账结果
					obj =JSON.parseObject(messageExtWrapper.getBody(), MQInsHandleDataInfo.class);
				}
			}
		} catch (Exception e) {
			logger.error("convert message to obj failed", e);
			throw e;
		}
		
		return obj;
    }
    
}
