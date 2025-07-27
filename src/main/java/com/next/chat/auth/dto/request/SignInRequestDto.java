package com.next.chat.auth.dto.request;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private String userId;
    private String password;
    private boolean isAuto;
}
