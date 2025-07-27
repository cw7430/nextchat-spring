package com.next.chat.auth.endpoint;


import com.next.chat.common.endpoint.EndPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthEndpoint {
    SIGN_IN_NATIVE(EndPoint.BASE + "/auth/sign_in_native", "로그인", "POST"),
    SIGN_UP_NATIVE(EndPoint.BASE + "/auth/sign_up_native", "회원가입", "POST"),
    CHECK_ID(EndPoint.BASE  + "/auth/check_id", "아이디 중복체크", "POST");

    private final String path;
    private final String description;
    private final String httpMethod;
}
