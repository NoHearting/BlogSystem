package com.example.backgroundsystem.controller;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.domain.CommentPage;
import com.example.backgroundsystem.service.BlogService;
import com.example.backgroundsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping({"/","index","index.html"})
    public String index(Map<String,Object> map){
        List<Blog> allBlog = blogService.getAllBlog(true);
        map.put("blogs",(allBlog==null)?new ArrayList<Blog>():allBlog);
        return "Blogs/index";
    }

    /**
     * 更新页面 update.html
     * @return
     */
    @GetMapping("update")
    public String update(){
        return "Blogs/update";
    }

    /**
     * 归档  archives.html
     * @return
     */
    @GetMapping("archives")
    public String archives(){
        return "Blogs/archives";
    }

    /**
     * 留言 gustbook.html
     * @return
     */
    @GetMapping("gustbook")
    public String gustbook(Integer pos,Integer currentPage, Integer pageMaxItems, Map<String,Object> map){
        try{
            CommentPage comments = commentService.getComments(pos, currentPage, pageMaxItems);
            map.put("commentPage",comments);
            return "Blogs/gustbook";
        }catch (Exception e){
            CommentPage comments = commentService.getComments(-1, 1, 10);
            map.put("commentPage",comments);
            return "Blogs/gustbook";
        }

    }

    /**
     * 链接 link.html
     * @return
     */
    @GetMapping("link")
    public String link(){
        return "Blogs/link";
    }

    /**
     * 搜索 search.html
     * @return
     */
    @GetMapping("search")
    public String search(){
        return "Blogs/search";
    }


    /**
     * 博客细节 details.html
     * @return
     */
    @GetMapping("detail")
    public String details(Integer id,Integer currentPage, Integer pageMaxItems,Map<String,Object> map){
        map.put("id",(Integer)id);
        CommentPage comments = commentService.getComments(id, currentPage, pageMaxItems);
        map.put("commentPage",comments);
        return "Blogs/detail";
    }


    @ResponseBody
    @RequestMapping("/allBlogs")
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlog(true);
    }


    @GetMapping("markdown")
    public String getBlogById(Integer id,Map<String,Object> map){
        Blog blog = blogService.getBlogById(id);
        map.put("id",id);
        map.put("blog",blog);
        return "Blogs/markdown";
    }

    @ResponseBody
    @RequestMapping("/getOne")
    public Blog getBlogById(Integer id){
        return blogService.getBlogById(id);
    }
}

