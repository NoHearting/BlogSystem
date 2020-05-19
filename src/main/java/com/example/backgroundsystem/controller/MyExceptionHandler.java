package com.example.backgroundsystem.controller;

import com.example.backgroundsystem.exception.BlogException;
import com.example.backgroundsystem.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(BlogException.class)
    public String handleException(Exception e, HttpServletRequest request){

        //设置自己的状态码，不然不能进入错误定制页面的解析流程
        request.setAttribute("javax.servlet.error.status_code",((MyException)e).getStatusCode() );
        //可以向request中传入想要加入的异常信息到前端
        //转发到/error,使用SpringBoot的处理器自适应，（浏览器访问返回值，客户端返回JOSN数据）
        return "forward:/error";
    }
}
