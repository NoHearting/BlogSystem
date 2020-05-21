package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.backgroundsystem.domain.BkSys.AdminUser;
import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.Tag;
import com.example.backgroundsystem.domain.page.BlogPage;
import com.example.backgroundsystem.domain.response.OperateBlogResponse;
import com.example.backgroundsystem.exception.BlogException;
import com.example.backgroundsystem.service.BkSysService;
import com.example.backgroundsystem.service.BlogService;
import com.example.backgroundsystem.utils.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("admin")
public class BkSysController {

    //返回视图和数据
    private ModelAndView mav = new ModelAndView();

    @Autowired
    private BkSysService bkSysService;

    @Autowired
    private BlogService blogService;

    @RequestMapping({"/","index","index.html"})
    public ModelAndView index(HttpServletRequest request){
        mav.clear();
        mav.setViewName("BkSys/index");
//        getUserInfoFormSession(request,mav);
        return mav;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request){
        mav.clear();
        mav.setViewName("BkSys/pages/login");
        AdminUser adminUser = findRememberPassword(request);
        if(adminUser != null){
            mav.addObject("adminUser",adminUser);   //传回记住密码的用户的登录密码
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
//        System.out.println("loginAction"+remember);
        if(bkSysService.login(username,password)){

            try {
                rememberPassword(remember,new AdminUser(-1,username,password),response);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("username",username);  //登录状态

//            mav.addObject("username",username);
            mav.addObject("error",null);
            mav.setViewName("BkSys/index");
//            System.out.println(mav);
            return mav;
        }else{
            System.out.println("账号或者密码错误");
            mav.addObject("error","账号或者密码错误");
            mav.setViewName("BkSys/pages/login");
            return mav;
        }
    }


    /**
     * 写博客
     * @param request
     * @return
     */
    @RequestMapping("addArticle")
    public ModelAndView addArticle(HttpServletRequest request){
        mav.clear();
        mav.setViewName("BkSys/pages/add-article");
        try{
            List<Tag> tags = blogService.listBlogTags();
            mav.addObject("tags",tags);
        }catch (Exception e){
            e.printStackTrace();  //应该抛出一个异常
        }
//        getUserInfoFormSession(request,mav);
        return mav;
    }


    /**
     * 插入博客
     * @param content
     * @param title
     * @param tags  博客的标签
     * @return
     */
    @RequestMapping("insertBlog")
    public ModelAndView insertBlog(String content, String title, String tags) throws Exception {
        mav.clear();
        try{
            Blog blog = new Blog();
            blog.setContent(content);
            blog.setTitle(title);
//            System.out.println(tags);
            blogService.insertBlog(blog,tags);
            System.out.println(tags);
//            return JSON.toJSONString(new operateBlogResponse(1,"添加成功"));
            mav.addObject("addBlog",new Blog(1,title,null,new MyDate(),0));
            mav.setViewName("BkSys/pages/success/success-add-article");
            return mav;
        }catch (Exception e){
//            return JSON.toJSONString(new operateBlogResponse(2,"添加失败"));
//            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new BlogException("添加博客失败，请重试",500);
        }
    }

    @ResponseBody
    @RequestMapping("deleteBlogs")
    public OperateBlogResponse deleteBlogs(String bIds){
        System.out.println(bIds);
        try{
            blogService.deleteBlogs(bIds);
            return new OperateBlogResponse(1,"删除成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new OperateBlogResponse(0,"出现了一点小问题！！！");
        }
    }
    /**
     * 所有博客页面，查询出第一页的博客
     * @return
     */
    @RequestMapping("blogs")
    public ModelAndView blogs(){
        mav.clear();
        mav.setViewName("BkSys/pages/blogs");
        BlogPage blogPage = blogService.listBlogForBkSys(1);//第一次获取页面默认获取第一页博客
        mav.addObject("blogPage",blogPage);
        return mav;
    }

    /**
     * 传递查询的博客列表的数据，为前端异步刷新博客列表做准备
     *
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping("updateBlogListBk")
    public BlogPage updateBlogList(int currentPage){
        return blogService.listBlogForBkSys(currentPage);
    }


    /**
     * 删除当前id的博客
     * @param bId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteBlog")
    public OperateBlogResponse deleteBlog(int bId){
        try{
            blogService.deleteBlog(bId);
            return new OperateBlogResponse(1,"删除成功");
        }catch (Exception e){
            return new OperateBlogResponse(0,e.getMessage());
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
    private AdminUser findRememberPassword(HttpServletRequest request){
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
