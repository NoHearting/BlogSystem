package com.example.backgroundsystem.domain.blogsys;

import lombok.*;

/**
 * 博客分类标签
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tag {

    @Getter
    @Setter
    private int tId;

    @Getter
    @Setter
    private String label;  // 标签名
}
