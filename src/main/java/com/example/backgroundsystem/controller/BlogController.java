package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.domain.page.CommentPage;
import com.example.backgroundsystem.service.BlogService;
import com.example.backgroundsystem.service.CommentService;
import com.example.backgroundsystem.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LinkService linkService;

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
    public String link(Map<String,Object>map){
        map.put("links",linkService.listLinked());
        return "Blogs/link";
    }

    /**
     * 搜索 search.html
     * 根据关键字返回符合条件的所有评论
     * @param s 搜索关键字
     * @param map 保存返回数据的容器
     * @return
     */
    @RequestMapping("search")
    public String search(String s,Map<String,Object>map){
        map.put("blogPage",blogService.getBlogsByKeywordAndPage(s,1,10));
        map.put("keyword",s);
        return "Blogs/search";
    }

    /**
     * 查询题目匹配关键字的博客，并返回具体某一页的博客
     * 用于Ajax的请求，返回数据
     * @param s  关键字
     * @param currentPage  当前页码
     * @return 返回Json数据，包含当前页码的满足条件的博客
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public String searchPage(String s,int currentPage){
        Map<String,Object> map = new HashMap<>();
        map.put("blogPage",blogService.getBlogsByKeywordAndPage(s,currentPage,10));
        map.put("keyword",s);
        return JSON.toJSONString(map);
    }




    /**
     * 博客细节 details.html
     * 返回博客页面的同时获取博客下的所有评论
     * 返回的博客评论使用翻页
     * @param id 博客id
     * @param currentPage 当前页码
     * @param pageMaxItems  页码最大评论条数
     * @param map 保存返回评论的容器
     * @return
     */
    @GetMapping("detail")
    public String details(Integer id,Integer currentPage, Integer pageMaxItems,Map<String,Object> map){
        map.put("id",(Integer)id);
        CommentPage comments = commentService.getComments(id, currentPage, pageMaxItems);
        map.put("commentPage",comments);
        return "Blogs/detail";
    }


    /**
     * 返回博客页面的同时获取博客下的所有评论
     * 返回的博客评论使用翻页，默认返回第一页，每页最大条数为10
     * @param id 博客id
     * @param map 保存返回评论的容器
     * @return
     */
    @RequestMapping("/detailDefault/{id}")
    public String detailsDefault(@PathVariable("id") Integer id, Map<String,Object> map){
        map.put("id",1);
        CommentPage comments = commentService.getComments(id, 1, 10);
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


    /**
     * 后台添加博客
     */
    @RequestMapping("/insertBlog")
    public void insertBlog(){

    }
}

