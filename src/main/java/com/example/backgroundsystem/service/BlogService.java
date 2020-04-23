package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.domain.BlogPage;

import java.util.List;

public interface BlogService {

    public List<Blog> getAllBlog(Boolean isCut);

    public void dealBlogData(List<Blog> blogs,Integer pos);

    public Blog getBlogById(Integer id);

    public List<Blog> getBlogsByKeyword(String s);

    public BlogPage getBlogsByKeywordAndPage(String keyword, int currentPage, int pageMaxItems);
}
