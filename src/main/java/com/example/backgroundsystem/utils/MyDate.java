package com.example.backgroundsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate extends Date {

    private String format = "yyyy-MM-dd";

    public MyDate() {
        super();
    }

    public MyDate(long date,String format){
        super(date);
        if(format!=null&&"".equals(format)) {
            this.format = format;
        }
    }


    public MyDate(long date){
        super(date);
    }


    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat(this.format);
        return format.format(this);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
