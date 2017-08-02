package com.eif.ftc.util;

import com.eif.ftc.util.exception.BusinessException;

import java.math.BigDecimal;

/**
 * Created by Matt on 16/1/22.
 * WTF!!!!!
 */
public class MoneyUtil {

	/**
	 * 展示精度
	 */
	public final static int DISPLAY_ACCURACY = 2;
	
	/**
	 * 计算精度
	 */
    public final static int ACCURACY = 6;
    
    public static BigDecimal divide(BigDecimal dividend,BigDecimal divisor)
    {
//        if (dividend == null || divisor == null || !(divisor.compareTo(BigDecimal.ZERO)==1))
//            throw new BusinessException();
        return dividend.divide(divisor,ACCURACY,BigDecimal.ROUND_DOWN);
    }
}
