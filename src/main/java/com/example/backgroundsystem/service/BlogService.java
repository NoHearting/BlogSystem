package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.page.BlogPage;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlog(Boolean isCut);

    void dealBlogData(List<Blog> blogs,Integer pos);

    Blog getBlogById(Integer id);

    List<Blog> getBlogsByKeyword(String s);

    BlogPage getBlogsByKeywordAndPage(String keyword, int currentPage, int pageMaxItems);

    BlogPage listBlogNoContent(int currentPage,int pageMaxItems);


    void insertBlog(Blog blog);
}
