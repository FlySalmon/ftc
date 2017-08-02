package com.eif.ftc.service.test.mq;

import com.eif.fis.facade.request.ftc.UnfreezeAndDeductionProdInventoryReq;
import com.eif.fis.facade.request.ftc.UnfreezeProdInventoryReq;
import com.eif.framework.mq.DefaultRMQProducer;
import com.eif.framework.test.listener.MockExecutionListener;
import com.eif.framework.test.runner.DefaultRunner;
import com.eif.framework.test.runner.WithAutoTransactionalJUnit4SpringContextRunner;
import com.eif.ftc.integration.fis.FisIntService;
import com.eif.ftc.service.constant.CheckRuleNameType;
import com.eif.ftc.service.mq.FundMQConsumerService;
import com.eif.ftc.util.exception.BusinessException;
import com.eif.inspection.facade.code.DiffType;
import com.eif.inspection.facade.mq.MQInsHandleDataInfo;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Brief:
 * Author: wuzhongxin
 * Date: 2017/7/31 10:19
 */

@DefaultRunner.AutoMockLevel(DefaultRunner.MockLevelEnum.SPY)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-service.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockExecutionListener.class, TransactionalTestExecutionListener.class })
@TransactionConfiguration
@Transactional
public class FundMQConsumerServiceTest extends WithAutoTransactionalJUnit4SpringContextRunner {

    @Mock
    DefaultRMQProducer producer;

    @Spy
    @Resource
    private FisIntService fisIntService;

    @InjectMocks
    @Autowired
    FundMQConsumerService fundMQConsumerService;

    @After
    public void after(){
        Mockito.reset(fisIntService);
    }

    @Test
    public void consumeInspectionInventoryDiffMessageTest(){
        //支付成功对账
        MQInsHandleDataInfo mqInsHandleDataInfo = new MQInsHandleDataInfo();
        mqInsHandleDataInfo.setCheckName(CheckRuleNameType.PAY_SUCC_STATUS_CHECK_RULE_NAME);
        mqInsHandleDataInfo.setCheckRuleName(CheckRuleNameType.PAY_SUCC_STATUS_CHECK_RULE_NAME);
        mqInsHandleDataInfo.setDataMarker("frozenCode=a0b7283fbe324ade89f39d9b3f80bcc3");
        mqInsHandleDataInfo.setDiffType(DiffType.notMapped);
        mqInsHandleDataInfo.setHandleDataNo("001");
        Mockito.doNothing().when(fisIntService).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));
        fundMQConsumerService.consumeInspectionInventoryDiffMessage(mqInsHandleDataInfo);

        Mockito.doThrow(new BusinessException("mockitoMsg", "mockitoEx"))
                .when(fisIntService).unfreezeAndDeductionProdInventory(Mockito.any(UnfreezeAndDeductionProdInventoryReq.class));
        fundMQConsumerService.consumeInspectionInventoryDiffMessage(mqInsHandleDataInfo);

        //支付失败对账
        MQInsHandleDataInfo mqInsHandleDataInfo2 = new MQInsHandleDataInfo();
        mqInsHandleDataInfo2.setCheckName(CheckRuleNameType.PAY_FAIL_STATUS_CHECK_RULE_NAME);
        mqInsHandleDataInfo2.setCheckRuleName(CheckRuleNameType.PAY_FAIL_STATUS_CHECK_RULE_NAME);
        mqInsHandleDataInfo2.setDataMarker("frozenCode=a0b7283fbe324ade89f39d9b3f80bcc3");
        mqInsHandleDataInfo2.setDiffType(DiffType.notMapped);
        mqInsHandleDataInfo2.setHandleDataNo("002");
        Mockito.doNothing().when(fisIntService).unfreezeProdInventory(Mockito.any(UnfreezeProdInventoryReq.class));
        fundMQConsumerService.consumeInspectionInventoryDiffMessage(mqInsHandleDataInfo2);

        Mockito.doThrow(new BusinessException("mockitoMsg", "mockitoEx"))
                .when(fisIntService).unfreezeProdInventory(Mockito.any(UnfreezeProdInventoryReq.class));
        fundMQConsumerService.consumeInspectionInventoryDiffMessage(mqInsHandleDataInfo2);
    }
}
