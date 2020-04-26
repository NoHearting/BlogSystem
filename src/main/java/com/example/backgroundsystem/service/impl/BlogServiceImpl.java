package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.domain.BlogNoContent;
import com.example.backgroundsystem.domain.page.BlogPage;
import com.example.backgroundsystem.mapper.BlogMapper;
import com.example.backgroundsystem.service.BlogService;
import com.example.backgroundsystem.service.utils.CommonUtils;
import com.example.backgroundsystem.service.utils.PageUtils;
import com.example.backgroundsystem.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    /**
     * 获取所有博客处理之后并返回,可以选择是否将博客内容截取（截断）
     * 在页面上需要简短显示，所以需要截取
     * @param isCut 判断是否截取博客内容
     * @return
     */
    @Override
    public List<Blog> getAllBlog(Boolean isCut) {
        try{
            List<Blog> allBlogs = blogMapper.getAllBlogs();

            //转换日期变量
            CommonUtils.dealDate(allBlogs);
            if(!isCut){
                return allBlogs;
            }else{
                dealBlogData(allBlogs,100);
                return allBlogs;
            }
        }catch (Exception e){
            LoggerUtils.warn(e.getMessage());
            return null;
        }
    }


    /**
     * 处理博客数据
     * 将博客内容截短，并用省略号代替
     * @param blogs
     * @param pos  截短的位置，或者截取的字符数量
     */
    @Override
    public void dealBlogData(List<Blog> blogs,Integer pos) {
        if(pos > 0) {
            for (int i = 0; i < blogs.size(); i++) {
                Blog blog = blogs.get(i);
                blog.setContent(blog.getContent().substring(0, Math.min(pos, blog.getContent().length())) + "…");
            }
        }
    }

    @Override
    public Blog getBlogById(Integer id) {
        try{
            Blog blog = blogMapper.getBlogById(id);
            return blog;
        }catch (Exception e){
            LoggerUtils.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Blog> getBlogsByKeyword(String s) {
        return blogMapper.getBlogsByKeyword(s);
    }

    @Override
    public BlogPage getBlogsByKeywordAndPage(String keyword, int currentPage, int pageMaxItems) {
        int totalItems = blogMapper.getBlogCountByKeyword(keyword);
        int begin = PageUtils.calBeginItemIndex(pageMaxItems,currentPage);
        int totalPages = PageUtils.calTotalPages(totalItems,pageMaxItems);
        List<Blog> blogs = blogMapper.getBlogsByKeywordAndPage(keyword, begin, pageMaxItems);
        CommonUtils.dealDate(blogs);   // 格式化日期为yyyy-MM-dd

        return new BlogPage(totalItems,totalPages,currentPage,pageMaxItems,blogs);
    }

    /**
     * 分页查询博客，但是不封装博客内容
     * 作为后台查询显示，减少内容传输的消耗
     * @param currentPage
     * @param pageMaxItems
     * @return
     */
    @Override
    public BlogPage listBlogNoContent(int currentPage, int pageMaxItems) {
        int totalItems = blogMapper.countBlog();
        int begin = PageUtils.calBeginItemIndex(pageMaxItems,currentPage);
        int totalPages = PageUtils.calTotalPages(totalItems,pageMaxItems);
        List<BlogNoContent> blogs = blogMapper.listBlogNoContent(begin, pageMaxItems);

        CommonUtils.dealDateExact(blogs);   // 格式化日期为yyyy-MM-dd HH:mm:ss
        LoggerUtils.debug(blogs.toString());
        return new BlogPage(totalItems,totalPages,currentPage,pageMaxItems,blogs);
    }


}
