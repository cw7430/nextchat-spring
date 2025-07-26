package com.next.chat.auth.mapper;

import com.next.chat.auth.dto.request.SignInRequestDto;
import com.next.chat.auth.dto.result.SignInResultDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    SignInResultDto findUserByUserIdForNativeSignIn(SignInRequestDto signInRequestDto);
}
