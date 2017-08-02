package com.eif.ftc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by bohan on 1/19/16.
 */
public class DateTimeUtils {

    public static Date getCurrentDay() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();

        return date;
    }

    public static Date getYesterDayBegin()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE,-1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    public static Date getYesterDayEnd()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();

        return date;
    }

    public static Date getDate(int yyyyMMdd) {
        int dd = yyyyMMdd % 100;
        int yyyyMM = yyyyMMdd / 100;
        int mm = yyyyMM % 100;
        int yyyy = yyyyMM / 100;
        GregorianCalendar d = new GregorianCalendar(yyyy, mm - 1, dd);
        return d.getTime();
    }
    
    public static String getHttpHeaderDate(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		return sdf.format(date);
    }
   
    public static void main(String[] args) {
    	System.out.println(getHttpHeaderDate(new Date()));
    }
}
