package com.next.chat.dto.result.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NativeSignInResultDto {
    private String userId;
    private String password;
    private String authorityLevel;
    private String authMethod;
}
