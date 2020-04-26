package com.example.backgroundsystem.domain.page;

import com.example.backgroundsystem.domain.Comment;
import lombok.*;

import java.util.List;

/**
 * 分页查询评论
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentPage {
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
    private List<Comment> comments;   // 查询的评论
}
