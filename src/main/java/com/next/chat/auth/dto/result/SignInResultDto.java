package com.next.chat.auth.dto.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResultDto {
    private String userId;
    private String password;
    private String authorityLevel;
}
