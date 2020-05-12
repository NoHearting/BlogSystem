package com.example.backgroundsystem.domain.blogsys;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Link {

    @Getter
    @Setter
    private int fId;

    @Getter
    @Setter
    private String friendName;  //友链用户的名字

    @Setter
    @Getter
    private String webUrl;     //友链地址


    @Getter
    @Setter
    private String headPicture;  //头像
}
