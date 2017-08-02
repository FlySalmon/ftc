package com.eif.ftc.facade.utils;

/**
 * Created by Matt on 2016/10/31.
 */
public class MaskPosition {

    private int maskStart;

    private int maskEnd;

    private static final String NULL_TEXT = "<null>";
    private static char MASK_CHAR = '*';
    private final static String ALL_MASKED = "*******";

    public MaskPosition(int maskStart, int maskEnd) {
        this.maskEnd = maskEnd;
        this.maskStart = maskStart;
    }

    public int getMaskStart() {
        return maskStart;
    }

    public void setMaskStart(int maskStart) {
        this.maskStart = maskStart;
    }

    public int getMaskEnd() {
        return maskEnd;
    }

    public void setMaskEnd(int maskEnd) {
        this.maskEnd = maskEnd;
    }

    public String filter(Object origin) {

        if (origin == null) {
            return NULL_TEXT;
        }
        String originStr = origin.toString();

        if (maskStart < 0 || maskEnd < 0) {
            return ALL_MASKED;
        }

        if (maskEnd > originStr.length()) {
            return originStr;
        }

        char[] originCharArray = originStr.toCharArray();
        for (int i = maskStart; i < maskEnd; i++) {
            originCharArray[i] = MASK_CHAR;
        }
        return new String(originCharArray);
    }
}
