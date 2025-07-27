package com.next.chat.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SignInResponseDto {
    private long accessTokenExpiresAt;
    private long refreshTokenExpiresAt;
    private String userAuthority;
    private String authMethod;
}
