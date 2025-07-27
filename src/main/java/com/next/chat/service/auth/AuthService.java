package com.next.chat.service.auth;

import com.next.chat.common.code.ResponseCode;
import com.next.chat.common.exception.CustomException;
import com.next.chat.dto.request.auth.NativeSignInRequestDto;
import com.next.chat.dto.response.auth.SignInResponseDto;
import com.next.chat.dto.result.auth.NativeSignInResultDto;
import com.next.chat.dto.service.auth.TokenServiceDto;
import com.next.chat.mapper.AuthMapper;
import com.next.chat.security.jwt.JwtProvider;
import com.next.chat.util.cookie.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private static TokenServiceDto createAccessToken(JwtProvider jwtProvider, String userId, String authorityLevel) {
        String token = jwtProvider.generateAccessToken(userId, authorityLevel);
        long expiresAt = jwtProvider.getAccessTokenExpiration(token);
        return new TokenServiceDto(token, expiresAt);
    }

    private static TokenServiceDto createRefreshToken(JwtProvider jwtProvider, String userId) {
        String token = jwtProvider.generateRefreshToken(userId);
        long expiresAt = jwtProvider.getRefreshTokenExpiration(token);
        return new TokenServiceDto(token, expiresAt);
    }

    private static String convertAuthority(String authority) {
        return "2".equals(authority) ? "ADMIN" : "USER";
    }

    public SignInResponseDto signInNative(HttpServletResponse response, NativeSignInRequestDto nativeSignInRequestDto) {
        NativeSignInResultDto result = authMapper.findUserByUserIdForNativeSignIn(nativeSignInRequestDto)
                .orElseThrow(() -> new CustomException(ResponseCode.LOGIN_ERROR));

        if (!passwordEncoder.matches(nativeSignInRequestDto.getPassword(), result.getPassword())) {
            throw new CustomException(ResponseCode.LOGIN_ERROR);
        }

        String userAuthority = convertAuthority(result.getAuthorityLevel());
        String authMethod = result.getAuthMethod();

        TokenServiceDto accessTokenServiceDto = createAccessToken(jwtProvider, result.getUserId(), result.getAuthorityLevel());
        CookieUtil.addCookie(response, "accessToken", accessTokenServiceDto.token(), false,-1);
        TokenServiceDto refreshTokenServiceDto = createRefreshToken(jwtProvider, result.getUserId());
        int refreshTokenMaxAge = -1;
        if (nativeSignInRequestDto.isAuto()) {
            refreshTokenMaxAge = (int) ((refreshTokenServiceDto.expiresAt() - System.currentTimeMillis()) / 1000);
        }
        CookieUtil.addCookie(response, "refreshToken", refreshTokenServiceDto.token(), true, refreshTokenMaxAge);

        return SignInResponseDto.builder()
                .authMethod(authMethod)
                .userAuthority(userAuthority)
                .accessTokenExpiresAt(accessTokenServiceDto.expiresAt())
                .refreshTokenExpiresAt(refreshTokenServiceDto.expiresAt())
                .build();
    }

}
