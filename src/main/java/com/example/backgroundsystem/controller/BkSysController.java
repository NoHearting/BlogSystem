package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backgroundsystem.domain.BkSys.AdminUser;
import com.example.backgroundsystem.service.BkSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@RequestMapping("admin2")
public class BkSysController {

    //返回视图和数据
    private ModelAndView mav = new ModelAndView();

    @Autowired
    private BkSysService bkSysService;

    @RequestMapping({"/","index","index.html"})
    public ModelAndView index(HttpServletRequest request){
        mav.clear();
        mav.setViewName("BkSys/index");
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
        if(user != null){
            mav.addObject("username",user);
        }
        return mav;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request){
        mav.clear();
        mav.setViewName("BkSys/pages/login");
        AdminUser adminUser = findrememberPassword(request);
        if(adminUser != null){
            mav.addObject("adminUser",adminUser);
        }
        return mav;
    }

    /**
     * 处理登录请求
     * @param username
     * @param password
     * @param remember
     * @param request
     * @return
     */
    @RequestMapping("loginAction")
    public ModelAndView loginAction(String username, String password, String remember, HttpServletRequest request,HttpServletResponse response){
        mav.clear();
        System.out.println(remember);
        if(bkSysService.login(username,password)){

            try {
                rememberPassword(remember,new AdminUser(-1,username,password),response);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("user",username);  //登录状态

            mav.addObject("username",username);
            mav.addObject("error",null);
            mav.setViewName("BkSys/index");
            System.out.println(mav);
            return mav;
        }else{
            mav.addObject("error","账号或者密码错误");
            mav.setViewName("BkSys/pages/login");
            return mav;
        }
    }

    /**
     * 判断是否记住密码，
     * 记住密码则创建一个cookie来存放密码，cookie的时限为1天，返回true
     * 不记住则返回false
     * @param remember
     * @param response
     * @return
     */
    private boolean rememberPassword(String remember,AdminUser adminUser,HttpServletResponse response) throws UnsupportedEncodingException {
        //记住密码
        if(null!=remember&&"on".equals(remember)){

            //将字符串先编码在放入cookie，防止cookie出现异常
            String encodeCookie = URLEncoder.encode(JSON.toJSONString(adminUser),"utf-8");
            Cookie cookie = new Cookie("remember",encodeCookie);
            cookie.setMaxAge(24*60*60);  //设置一天的生存时间
            response.addCookie(cookie);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 查找是否记住了密码，
     * 如果是则返回记住的用户名和密码
     * 没有记住则什么都不做
     * @param request
     * @return
     */
    private AdminUser findrememberPassword(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();//获取保存在request的所有cookie
        if(cookies != null){//判断cookies数组是否为空
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("remember")){//通过for循环找到想要获取的值
                    System.out.println(cookie.getValue());//控制台输出
                    String decodeCookie = URLDecoder.decode(cookie.getValue());
                    JSONObject jsonObject = JSON.parseObject(decodeCookie);//将json字符串转化为对象
                    AdminUser adminUser = jsonObject.toJavaObject(AdminUser.class);
                    return adminUser;
                }
            }
        }
        return  null;

    }
}
