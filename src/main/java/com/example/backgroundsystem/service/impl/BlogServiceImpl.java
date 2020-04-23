package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.mapper.BlogMapper;
import com.example.backgroundsystem.service.BlogService;
import com.example.backgroundsystem.utils.LoggerUtils;
import com.example.backgroundsystem.utils.MyDate;
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
            dealData(allBlogs);
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

    /**
     * 处理博客信息
     * 将博客创作日期格式化为 yyyy-MM-dd HH:mm:ss
     * @param blogs
     */
    public void dealData(List<Blog> blogs){
        for(int i = 0;i<blogs.size();i++){
            Blog blog = blogs.get(i);
            // MyDate默认格式化为yy-MM-dd
            blog.setWriteTime(new MyDate(blog.getWriteTime().getTime()));
        }
    }
}
