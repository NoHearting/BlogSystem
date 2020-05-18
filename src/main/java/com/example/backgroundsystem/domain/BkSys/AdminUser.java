package com.example.backgroundsystem.domain.BkSys;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminUser {

    @Getter
    @Setter
    private int aId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
