package com.next.chat.dto.request.auth;

import lombok.Getter;

@Getter
public class NativeSignInRequestDto {
    private String userId;
    private String password;
    private boolean isAuto;
}
