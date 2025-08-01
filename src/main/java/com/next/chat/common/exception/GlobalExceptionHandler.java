package com.next.chat.common.exception;

import com.next.chat.dto.response.ResponseDto;
import com.next.chat.common.code.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 유효성 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(ResponseCode.VALIDATION_ERROR.getMessage());

        log.error("Validation failed: {}", errorMessage);

        return new ResponseEntity<>(
                ResponseDto.fail(ResponseCode.VALIDATION_ERROR, errorMessage),
                ResponseCode.VALIDATION_ERROR.getStatus()
        );
    }

    // 커스텀 비즈니스 예외
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto<Void>> handleCustomException(CustomException ex) {
        log.error("Custom exception occurred: {}", ex.getResponseCode().getMessage());
        return new ResponseEntity<>(
                ResponseDto.fail(ex.getResponseCode()),
                ex.getResponseCode().getStatus()
        );
    }

    // 데이터베이스 오류
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDto<Void>> handleDatabaseException(DataAccessException ex) {
        log.error("Database exception occurred", ex);
        return new ResponseEntity<>(ResponseDto.fail(ResponseCode.DATABASE_ERROR), ResponseCode.DATABASE_ERROR.getStatus());
    }

    // 기타 서버 오류
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleGeneralException(Exception ex) {
        log.error("Unhandled exception occurred", ex);
        return new ResponseEntity<>(
                ResponseDto.fail(ResponseCode.INTERNAL_SERVER_ERROR),
                ResponseCode.INTERNAL_SERVER_ERROR.getStatus()
        );
    }
}
