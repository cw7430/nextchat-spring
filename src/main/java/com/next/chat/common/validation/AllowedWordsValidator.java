package com.next.chat.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/**
 * @AllowedWords 의 실제 검사 로직.
 * null 허용, 허용 리스트에 포함되지 않으면 false
 */
public class AllowedWordsValidator implements ConstraintValidator<AllowedWords, String> {

    private List<String> allowedWords;

    @Override
    public void initialize(AllowedWords constraintAnnotation) {
        allowedWords = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return allowedWords.contains(value);
    }
}
