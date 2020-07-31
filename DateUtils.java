package com.sapience.admin.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public DateUtils() {
    }

    public String getPreviousDate(int days) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DATE, -days);
        String startDate = simpleDateFormat.format(cal.getTime());
        return startDate;
    }

    public String getPreviousNormalDate(int days) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        String startDate = simpleDateFormat.format(cal.getTime());
        return startDate;
    }

    public String getFutureDate(int days) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DATE, days);
        String startDate = simpleDateFormat.format(cal.getTime());
        return startDate;
    }

    public String getCurrentDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String currentDate = simpleDateFormat.format(cal.getTime());
        return currentDate;
    }

    public String addMinutestToString(String startTime )
    {
        String endTime = startTime;
        int interval = 1;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        try
        {
            Date date = df.parse(startTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, interval);
            cal.add(Calendar.SECOND, 1);
            endTime = String.valueOf(df.format(cal.getTime()));
        }
        catch (Exception e)
        {
            return endTime;
        }
       return endTime;

    }

    public void getCurrentWeek()
    {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DATE, -7);
        String firstDateOfPreviousWeek = simpleDateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String lastDateOfPreviousWeek = simpleDateFormat.format(cal.getTime());
        System.out.println(firstDateOfPreviousWeek + " - " + lastDateOfPreviousWeek);

    }
}
