package com.next.chat.mapper;

import com.next.chat.dto.request.auth.SignInRequestDto;
import com.next.chat.dto.result.auth.SignInResultDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    SignInResultDto findUserByUserIdForNativeSignIn(SignInRequestDto signInRequestDto);
}
