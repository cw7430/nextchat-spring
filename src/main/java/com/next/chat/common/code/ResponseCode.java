package com.next.chat.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    // 200 OK
    SUCCESS("SU", "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),

    // 400 Bad Request
    VALIDATION_ERROR("VE", "입력값이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_RESOURCE("DR", "이미 존재하는 항목입니다.", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("RNF", "요청한 자원을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

    // 401 Unauthorized
    UNAUTHORIZED("UA", "로그인이 필요합니다.", HttpStatus.UNAUTHORIZED),
    LOGIN_ERROR("LGE", "아이디 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),
    OAUTH2_AUTHENTICATION_FAILED("OAF", "소셜 로그인에 실패했습니다.", HttpStatus.UNAUTHORIZED),

    // 403 Forbidden
    FORBIDDEN("FB", "접근 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 404 Not Found
    ENDPOINT_NOT_FOUND("ENF", "요청한 경로가 잘못되었습니다.", HttpStatus.NOT_FOUND),

    // 409 Conflict
    CONFLICT("CF", "요청이 현재 상태와 충돌합니다.", HttpStatus.CONFLICT),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR("ISE", "서버에서 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR("DBE", "데이터베이스 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
