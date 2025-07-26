package com.next.chat.auth.mapper;

import com.next.chat.auth.dto.inner.response.SignInInnerResponseDto;
import com.next.chat.auth.dto.outer.request.SignInOuterRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    SignInInnerResponseDto findUserByUserIdForNativeSignIn(SignInOuterRequestDto signInOuterRequestDto);
}
