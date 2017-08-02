package com.eif.ftc.integration.util;

import com.eif.framework.common.utils.code.CommonRspCode;
import com.eif.framework.common.utils.code.RspCode;
import com.eif.ftc.util.PropertyUtils;
import com.eif.ftc.util.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bohan on 1/22/16.
 */
public final class ResponseMapper {

    public static final String MEMBER_SYS_CODE = "004";
    public static final String FIS_SYS_CODE = "001";
    public static final String TRANSCORE_SYS_CODE = "009";
    public static final String OFC_SYS_CODE = "034";
    public static final String PAYCORE_SYS_CODE = "008";
    public static final String RISK_SYS_CODE = "020";
    public static final String MARKET_SYS_CODE = "028";

    static Map<String, String> codeMap = new HashMap<>();

    static Logger logger = LoggerFactory.getLogger(ResponseMapper.class);

    static {
        codeMap = PropertyUtils.getPropertyMap("code.properties");
    }

    public static RspCode wrapBusinessException(String code, String msg) {
        return wrapBusinessException(code, msg, true);
    }

    public static RspCode wrapBusinessException(String code, String msg, boolean needToThrow) {
        if (code.startsWith(TRANSCORE_SYS_CODE) ||
                code.startsWith(FIS_SYS_CODE) ||
                code.startsWith(MEMBER_SYS_CODE) ||
                code.startsWith(RISK_SYS_CODE) ||
                code.startsWith(MARKET_SYS_CODE)) {
            for (Map.Entry<String, String> entry : codeMap.entrySet()) {
                if (entry.getValue().contains(code)) {
                    RspCode ftcRespCode = RespCodeEnumUtils.getFTCEnum(entry.getKey());
                    if (needToThrow) {
                        if (ftcRespCode != null) {
                            throw new BusinessException(ftcRespCode.getMessage(), ftcRespCode.getCode());
                        }
                    } else {
                        if (ftcRespCode != null) {
                            return ftcRespCode;
                        }
                    }
                }
            }
        } else if (code.startsWith(PAYCORE_SYS_CODE)) {
        } else if (code.startsWith(OFC_SYS_CODE)) {
        } else if (code.startsWith(MEMBER_SYS_CODE)) {
        } else if (code.startsWith(MARKET_SYS_CODE)) {
        }

        if (needToThrow) {
            throw new BusinessException(msg, CommonRspCode.SYS_ERROR.getCode());
        } else
            return CommonRspCode.SYS_ERROR;

    }
}
