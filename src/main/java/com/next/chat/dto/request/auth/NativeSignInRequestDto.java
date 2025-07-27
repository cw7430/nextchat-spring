package com.next.chat.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NativeSignInRequestDto {
    @NotBlank(message = "아이디와 비밀번호를 입력해주세요.")
    private String userId;
    @NotBlank(message = "아이디와 비밀번호를 입력해주세요.")
    private String password;
    private boolean isAuto;
}
