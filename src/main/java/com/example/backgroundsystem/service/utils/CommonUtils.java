package com.example.backgroundsystem.service.utils;

import com.example.backgroundsystem.domain.BaseBlog;
import com.example.backgroundsystem.domain.Blog;
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
}
