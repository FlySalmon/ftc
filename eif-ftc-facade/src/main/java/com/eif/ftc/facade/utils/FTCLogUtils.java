package com.eif.ftc.facade.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;


public class FTCLogUtils {
    private static final Logger logger = LoggerFactory.getLogger(FTCLogUtils.class);

    private static final Map<MaskPosition, List<String>> MASK_WORDS = new HashMap<>();

    private final static List<String> EXCLUDE_MASK_WORDS = new ArrayList<>();

    static {

        List<String> phones = new ArrayList<>();
        phones.add("PHONENO");
        phones.add("MOBILENO");
        phones.add("BANKCARDVERIFIEDMOBILE");
        phones.add("PHONENUMBER");
        phones.add("PHONENOS");
        phones.add("BINDPHONENO");
        phones.add("MOBILEPHONE");
        phones.add("USERPHONENO");
        phones.add("FRIENDPHONENO");
        phones.add("VERIFIEDMOBILE");
        phones.add("MEMBERPHONENUMBER");

        List<String> idCards = new ArrayList<>();
        idCards.add("IDCARDNO");
        idCards.add("BUSSINESSLEGALIDNO");
        idCards.add("IDNO");
        idCards.add("CERTNO");
        idCards.add("CARTNO");
        idCards.add("IDNOENC");

        List<String> names = new ArrayList<>();
        names.add("REALNAME");
        names.add("BUSSINESSLEGALNAME");
        names.add("NAME");
        names.add("SETTLEACCOUNTHOLDER");

        List<String> cardNos = new ArrayList<>();
        cardNos.add("CARDNO");
        cardNos.add("BANKCARDNO");

        MASK_WORDS.put(new MaskPosition(0, 13), cardNos);
        MASK_WORDS.put(new MaskPosition(0, 1), names);
        MASK_WORDS.put(new MaskPosition(6, 14), idCards);
        MASK_WORDS.put(new MaskPosition(3, 7), phones);

        EXCLUDE_MASK_WORDS.add("PAYMENTPWD");
        EXCLUDE_MASK_WORDS.add("BYTPK");
        EXCLUDE_MASK_WORDS.add("BYKEK");
        EXCLUDE_MASK_WORDS.add("NEWPWD");
        EXCLUDE_MASK_WORDS.add("EXPIREDSTRING");
        EXCLUDE_MASK_WORDS.add("PASSWORD");

    }

    public static String toFilterString(Object object) {

        if (object == null) {
            return null;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        ReflectionToStringBuilder refBuilder = new ReflectionToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE) {
            protected boolean accept(Field f) {
                return super.accept(f)
                        && !f.getName().toUpperCase().equals("PASSWORD")
                        && !f.getName().toUpperCase().equals("PAYMENTPWD")
                        && !f.getName().toUpperCase().equals("BYTPK")
                        && !f.getName().toUpperCase().equals("BYKEK")
                        && !f.getName().toUpperCase().equals("NEWPWD")
                        && !f.getName().toUpperCase().equals("EXPIREDSTRING")
                        && !f.getName().toUpperCase().equals("IDADDR")
                        && !f.getName().toUpperCase().equals("BIRTHDAY");
            }
        };
        Map<String, MaskPosition> maskFieldMap = new HashMap<>();
        List<String> excludeFields = new ArrayList<>();

        for (Field field : fields) {
            // mask field
            for (Map.Entry<MaskPosition, List<String>> entry : MASK_WORDS.entrySet()) {
                for (String keys : entry.getValue()) {
                    if (field.getName().toUpperCase().equals(keys)) {
                        maskFieldMap.put(field.getName(), entry.getKey());
                        break;
                    }
                }
            }
            // exclude field
            for (String exclude : EXCLUDE_MASK_WORDS) {
                if (field.getName().toUpperCase().equals(exclude)) {
                    excludeFields.add(field.getName());
                    break;
                }
            }
        }

        Set<String> maskFields = maskFieldMap.keySet();

        //忽略过滤字段&&自定义字段
        refBuilder.setExcludeFieldNames(maskFields.toArray(new String[maskFields.size()]));
        appendMaskFields(refBuilder, object, maskFieldMap);


        return refBuilder.toString();
    }

    private static void appendMaskFields(ReflectionToStringBuilder builder, Object object, Map<String, MaskPosition> maskFieldMap) {
        try {
            for (String fieldName : maskFieldMap.keySet()) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(object);

                MaskPosition maskPosition;
                if (fieldName.toUpperCase().equals("NAME") && fieldValue != null && StringUtils.isNumeric(fieldValue.toString())) {
                    maskPosition = new MaskPosition(3, 7);
                } else {
                    maskPosition = maskFieldMap.get(fieldName);
                }

                builder.append(fieldName, maskPosition.filter(fieldValue));
            }
        } catch (IllegalAccessException e) {
            logger.info("appendMaskFields failed", e);
        } catch (NoSuchFieldException e) {
            logger.info("appendMaskFields failed", e);
        }
    }
}
