package com.example.backgroundsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping({"/","index","index.html"})
    public String index(){
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
    public String gustbook(){
        return "Blogs/gustbook";
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
    public String details(){
        return "Blogs/detail";
    }
}

