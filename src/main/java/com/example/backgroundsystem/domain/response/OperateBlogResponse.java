package com.example.backgroundsystem.domain.response;

import lombok.*;

/**
 * 添加博客后的返回对象
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperateBlogResponse {
    @Getter
    @Setter
    private int status;

    @Getter
    @Setter
    private String info;
}
