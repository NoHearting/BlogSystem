package com.example.backgroundsystem.service.utils;

import com.example.backgroundsystem.domain.blogsys.*;
import com.example.backgroundsystem.mapper.CommentMapper;
import com.example.backgroundsystem.utils.DateUtils;
import com.example.backgroundsystem.utils.HtmlToPlainText;
import com.example.backgroundsystem.utils.MarkdownToHtml;
import com.example.backgroundsystem.utils.MyDate;

import java.util.Date;
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


    /**
     * 作用与归档，由于页面并不需要显示内容，但需要显示日期的特殊形式，
     * 因为日期的形式只需要显示为字符串，所以将日期转化后写入内容中
     *
     * @param blogs
     */
    public static void convertDateToContent(List<Blog> blogs){
        for(int i = 0;i<blogs.size();i++){
            Blog blog = blogs.get(i);
            DateUtils.setDate(blog.getWriteTime());
            blog.setContent(DateUtils.getMonth()+"-"+DateUtils.getDay());
        }
    }

    /**
     * 判断此条评论是否为一条回复评论
     * 若是，则对回复内容进行一次加工，加上所回复的评论的信息
     *      加工信息为：“回复+{评论人的昵称}:”,:作为分隔符
     * 若不是，则什么都不做
     * @param comment
     */
    public static void dealCommentIsReply(Comment comment,CommentMapper commentMapper){
        if(null!=comment){
            if(comment.getReply() > -1){
                String info = "回复 "+commentMapper.getNicknameById(comment.getReply())+": ";
                comment.setContent(info+comment.getContent());
            }else{
                return;
            }
        }
    }
}
