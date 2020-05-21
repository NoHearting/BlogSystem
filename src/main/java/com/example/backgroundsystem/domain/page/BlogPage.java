package com.example.backgroundsystem.domain.page;

import com.example.backgroundsystem.domain.blogsys.BaseBlog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * 分页查询博客
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })  //在json序列化时忽略bean中的一些不需要转化的属性,不然会报错
public class BlogPage {

    @Getter
    @Setter
    private int totalItems;   // 总的条目数

    @Getter
    @Setter
    private int totalPages;   // 总的分页数

    @Getter
    @Setter
    private int currentPage;  // 当前页面

    @Getter
    @Setter
    private int pageMaxItems;  //单页最多的条目

    @Getter
    @Setter
    private List<? extends BaseBlog> blogs;   // 查询的评论
}
