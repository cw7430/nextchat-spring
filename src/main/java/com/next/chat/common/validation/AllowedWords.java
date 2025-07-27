package com.next.chat.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 지정된 값만 허용되는 유효성 검사.
 * 예: @AllowedWords({"USER", "ADMIN"}) → 필드 값이 그 외면 검증 실패
 */
@Documented
@Constraint(validatedBy = AllowedWordsValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedWords {
    String message() default "허용되지 않은 단어입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] value();
}
