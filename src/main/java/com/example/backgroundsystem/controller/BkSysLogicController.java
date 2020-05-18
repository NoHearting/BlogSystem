package com.example.backgroundsystem.controller;


import com.alibaba.fastjson.JSON;
import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.response.operateBlogResponse;
import com.example.backgroundsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin2")
public class BkSysLogicController {

    @Autowired
    private BlogService blogService;

    @ResponseBody
    @RequestMapping("insertBlog")
    public String insertBlog(String content,String title){
        try{
            Blog blog = new Blog();
            blog.setContent(content);
            blog.setTitle(title);
            blogService.insertBlog(blog);
            return JSON.toJSONString(new operateBlogResponse(1,"添加成功"));
        }catch (Exception e){
            return JSON.toJSONString(new operateBlogResponse(2,"添加失败"));
        }
    }

    /**
     * 删除当前id的博客
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteBlog")
    public String deleteBlog(int id){
        try{
            blogService.deleteBlog(id);
            return JSON.toJSONString(new operateBlogResponse(1,"删除成功"));
        }catch (Exception e){
            return JSON.toJSONString(new operateBlogResponse(1,e.getMessage()));
        }
    }

}
