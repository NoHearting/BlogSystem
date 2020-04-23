package com.example.backgroundsystem.domain;

import lombok.*;

import java.util.List;

/**
 * 分页查询博客
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private List<Blog> blogs;   // 查询的评论
}
