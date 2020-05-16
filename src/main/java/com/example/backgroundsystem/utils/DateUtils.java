package com.example.backgroundsystem.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static Calendar calendar = Calendar.getInstance();


    public DateUtils(){}

    public static void setDate(Date targetDate){
        calendar.setTime(targetDate);
    }
    public static int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(){
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
