package com.next.chat.auth.dto.inner.response;

import lombok.*;

@Getter
@Setter
public class SignInInnerResponseDto {
    private String userId;
    private String password;
    private String authorityLevel;
}
