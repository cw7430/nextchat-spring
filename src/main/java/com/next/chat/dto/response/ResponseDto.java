package com.next.chat.dto.response;

import com.next.chat.common.code.ResponseCode;

public record ResponseDto<T>(String code, String message, T result) {
    public static <T> ResponseDto<T> success(T result) {
        return new ResponseDto<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), result);
    }

    public static <T> ResponseDto<T> success() {
        return new ResponseDto<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResponseDto<T> fail(ResponseCode responseCode) {
        return new ResponseDto<>(responseCode.getCode(), "", null);
    }

    public static <T> ResponseDto<T> fail(ResponseCode responseCode, String customMessage) {
        return new ResponseDto<>(responseCode.getCode(), customMessage, null);
    }
}