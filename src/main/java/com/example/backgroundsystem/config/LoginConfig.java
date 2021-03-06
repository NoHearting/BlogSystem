package com.example.backgroundsystem.config;

import com.example.backgroundsystem.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        registration.addPathPatterns("/admin3/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/admin/login",            //登录页面
                "/admin/loginAction",     //登录请求
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/Blogs/**",             //不拦截博客页面的获取
                "/**/*.ttf"
        );
    }
}
