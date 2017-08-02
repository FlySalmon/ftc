package com.eif.ftc.util;

import com.eif.framework.common.id.ZkIdGenerator;
import com.eif.framework.common.id.impl.ZkId17Generator;
import com.eif.framework.common.id.impl.ZkId20Generator;
import com.eif.framework.common.utils.constant.BizSysCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by bohan on 12/27/15.
 */
@Component
public class SequenceGenerator {

    @Resource(name="zkId20Generator")
    private ZkIdGenerator zkId20Generator;

    @Resource(name="zkId17Generator")
    private ZkIdGenerator zkId17Generator;

    private static final int SEQ_SIZE = 32;
    private static final int TA_SEQ_SIZE = 24;

    public String transOrderGen(int transType) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder(SEQ_SIZE);
        // 最前面20位全局唯一, 前4位代表系统号, 3位代表交易类型, 001代表基金交易模块号, 后2位随机数
        builder.append(zkId20Generator.genId()).append("0").append(BizSysCode.FTC_SYSTEM).append(String.format("%03d", transType)).append("001").append(String.format("%02d", random.nextInt(99)));

        return builder.toString();
    }

    public String acctOrderGen(int transType) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder(SEQ_SIZE);
        // 最前面20位全局唯一, 前4位代表系统号, 3位代表交易类型, 002代表基金账户模块号, 后2位随机数
        builder.append(zkId20Generator.genId()).append("0").append(BizSysCode.FTC_SYSTEM).append(String.format("%03d", transType)).append("001").append(String.format("%02d", random.nextInt(99)));

        return builder.toString();
    }

    public String taSeqNoGen(String businessCode) {
        StringBuilder builder = new StringBuilder(TA_SEQ_SIZE);
        builder.append(StringUtils.leftPad(businessCode, 4, "0")).append(zkId20Generator.genId());

        return builder.toString();
    }

    //基金交易账号
    public String transAccGen() {
        return zkId17Generator.genId();
    }


    public String amcNoGen(String type)
    {
        StringBuilder builder = new StringBuilder(SEQ_SIZE);
        builder.append(zkId20Generator.genId())
                .append("0")
                .append(BizSysCode.FTC_SYSTEM)
                .append(type);
        return builder.toString();
    }

}
