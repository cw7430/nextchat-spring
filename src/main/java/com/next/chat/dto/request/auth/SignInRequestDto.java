package com.next.chat.dto.request.auth;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
    private boolean isAuto;
}
