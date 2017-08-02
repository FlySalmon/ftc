package com.eif.ftc.integration.util;

import com.eif.framework.common.utils.code.RspCode;
import com.eif.ftc.facade.code.FTCRespCode;
import com.eif.transcore.facade.response.common.TransRspCode;

import java.util.Enumeration;

/**
 * Created by bohan on 2/17/16.
 */
public class RespCodeEnumUtils {

    public static RspCode getTCEnum(String code) {
        RspCode[] crc = TransRspCode.values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(code)) {
                return crc[i];
            }
        }
        return null;
    }
    public static RspCode getFTCEnum(String code) {
        RspCode[] crc = FTCRespCode.values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(code)) {
                return crc[i];
            }
        }
        return null;
    }
}

