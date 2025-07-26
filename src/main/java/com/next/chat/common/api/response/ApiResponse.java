package com.next.chat.common.api.response;

import com.next.chat.common.code.ResponseCode;

public record ApiResponse<T>(String code, String message, T result) {
    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                result
        );
    }

    public static <T> ApiResponse<T> fail(ResponseCode responseCode) {
        return new ApiResponse<>(
                responseCode.getCode(),
                responseCode.getMessage(),
                null
        );
    }
}
