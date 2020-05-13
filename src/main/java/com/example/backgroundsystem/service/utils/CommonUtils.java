package com.example.backgroundsystem.service.utils;

import com.example.backgroundsystem.domain.blogsys.BaseBlog;
import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.BlogWithCountComment;
import com.example.backgroundsystem.domain.blogsys.UpdateEvent;
import com.example.backgroundsystem.mapper.CommentMapper;
import com.example.backgroundsystem.utils.HtmlToPlainText;
import com.example.backgroundsystem.utils.MarkdownToHtml;
import com.example.backgroundsystem.utils.MyDate;

import java.util.List;

public class CommonUtils {


     /**
     * 处理博客信息
     * 将博客创作日期格式化为 yyyy-MM-dd
     * @param blogs
     */
     public static void dealDate(List<? extends BaseBlog> blogs){
        for(int i = 0;i<blogs.size();i++){
            BaseBlog blog = blogs.get(i);
            // MyDate默认格式化为yy-MM-dd
            blog.setWriteTime(new MyDate(blog.getWriteTime().getTime()));
        }
    }

    public static void dealEventDate(List<UpdateEvent> events){
        for(int i = 0;i<events.size();i++){
            UpdateEvent updateEvent = events.get(i);
            // MyDate默认格式化为yy-MM-dd
            updateEvent.setExecTime(new MyDate(updateEvent.getExecTime().getTime()));
        }
    }

    /**
     * 处理博客信息
     * 将博客创作日期格式化为 yyyy-MM-dd HH:mm:ss
     * @param blogs
     */
    public static void dealDateExact(List<? extends BaseBlog> blogs){
        for(int i = 0;i<blogs.size();i++){
            BaseBlog blog = blogs.get(i);
            // MyDate默认格式化为yy-MM-dd
            blog.setWriteTime(new MyDate(blog.getWriteTime().getTime(),"yyyy-MM-dd HH:mm:ss"));
        }
    }

    /**
     * 将博客内容（markdown格式）转化为纯文本格式
     * @param blogs
     */
    public static void dealBlogContent(List<? extends BaseBlog> blogs){
        for(int i = 0;i<blogs.size();i++){
            Blog blog = (Blog)blogs.get(i);
            blog.setContent(HtmlToPlainText.convert(MarkdownToHtml.convert(blog.getContent())));
        }
    }

    /**
     * 添加博客的评论条数
     * @param blogs
     */
    public static void addBlogCommentCount(List<Blog> blogs, CommentMapper commentMapper){
        for(int i = 0;i<blogs.size();i++){
            Blog blog = (Blog)blogs.get(i);
            BlogWithCountComment blogWithCountComment = new BlogWithCountComment(blog, commentMapper.getTotalCountById(blog.getbId()));
            blogs.set(i,blogWithCountComment);
        }
    }


}
