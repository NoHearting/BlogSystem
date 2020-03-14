package com.example.backgroundsystem.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);

    public static void debug(String s){
        LOGGER.debug(s);
    }

    public static void error(String s){
        LOGGER.error(s);
    }

    public static void warn(String s){
        LOGGER.warn(s);
    }

    public static void info(String s){
        LOGGER.info(s);
    }
}
