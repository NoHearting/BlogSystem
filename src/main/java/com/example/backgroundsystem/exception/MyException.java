package com.example.backgroundsystem.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

public class MyException extends Exception{

    @Getter
    @Setter
    private int statusCode;   // 错误后返回的状态码

    private String message;      //错误信息

    MyException(String message){
        super(message);
    }

    MyException(String message,int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

}
