package com.example.backgroundsystem.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 在容器中加入我们自己定义的ErrorAttributes
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回值的map就是页面和Json能获取的数据
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        //当前ErrorAttributes要携带的数据
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("company","top.zsj");

        //异常处理器中携带的数据,获取之后放入errorAttributes才能真正传入前端页面
//        Map<String,Object> ext = (Map<String,Object>)webRequest.getAttribute("ext", 0);  //0代表从reques域中获取数据
//        errorAttributes.put("ext",ext);
        return errorAttributes;
    }



}
