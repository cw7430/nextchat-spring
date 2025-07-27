package com.next.chat.service.auth;

import com.next.chat.dto.service.auth.TokenServiceDto;
import com.next.chat.mapper.AuthMapper;
import com.next.chat.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;

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


}
