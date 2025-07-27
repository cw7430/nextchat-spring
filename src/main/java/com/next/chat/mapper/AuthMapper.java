package com.next.chat.mapper;

import com.next.chat.dto.request.auth.NativeSignInRequestDto;
import com.next.chat.dto.result.auth.NativeSignInResultDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AuthMapper {
    Optional<NativeSignInResultDto> findUserByUserIdForNativeSignIn(NativeSignInRequestDto nativeSignInRequestDto);
}
