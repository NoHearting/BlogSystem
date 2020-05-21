package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.Tag;
import com.example.backgroundsystem.domain.page.BlogPage;

import java.util.List;
import java.util.Map;

public interface BlogService {

    List<Blog> getAllBlog(Boolean isCut);

    void dealBlogData(List<Blog> blogs,Integer pos);

    Blog getBlogById(Integer id);

    List<Blog> getBlogsByKeyword(String s);

    BlogPage getBlogsByKeywordAndPage(String keyword, int currentPage, int pageMaxItems);

    BlogPage listBlogNoContent(int currentPage,int pageMaxItems);

    Map<String, List<Blog>> calBlogByDate();

    void insertBlog(Blog blog, int tags);

    Blog getPreBlog(int id);

    Blog getAfterBlog(int id);

    Blog getCurrentBlog(Integer id);

    void addBlogReadTimes(int id);

    void deleteBlog(int id);
    void deleteBlogs(String bIds);

    List<Tag> listBlogTags();

    BlogPage listBlogForBkSys(int currentPage);
}
