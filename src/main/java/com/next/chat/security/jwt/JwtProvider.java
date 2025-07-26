package com.next.chat.security.jwt;

import com.next.chat.entities.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key accessKey;
    private final long accessTokenExpireTime;
    private final Key refreshKey;
    private final long refreshTokenExpireTime;

    public JwtProvider(
            @Value("${jwt.access.secret}") String accessSecretKey,
            @Value("${jwt.access.expiration}") long accessTokenExpireTime,
            @Value("${jwt.refresh.secret}") String refreshSecretKey,
            @Value("${jwt.refresh.expiration}") long refreshTokenExpireTime
    ) {
        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
        this.accessTokenExpireTime = accessTokenExpireTime;

        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
        this.refreshTokenExpireTime = refreshTokenExpireTime;
    }

    // 1. JWT 생성
    public String generateAccessToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpireTime);

        return Jwts.builder()
                .setSubject(user.getUserId())
                .claim("authorityLevel", user.getAuthorityLevel())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenExpireTime);

        return Jwts.builder()
                .setSubject(user.getUserId())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. JWT 검증
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(accessKey)
                    .build()
                    .parseClaimsJws(token); // 여기서 오류나면 예외 발생
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(refreshKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 3. 클레임 추출
    public Claims getAccessClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getRefreshClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(refreshKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 4. 정보 추출
    public long getAccessTokenExpiration(String token) {
        Claims claims = getAccessClaims(token);
        return claims.getExpiration().getTime();
    }

    public long getRefreshTokenExpiration(String token) {
        Claims claims = getRefreshClaims(token);
        return claims.getExpiration().getTime();
    }

    public String getUserIdFromAccessToken(String token) {
        return getAccessClaims(token).getSubject(); // "subject"는 userId
    }

    public String getUserIdFromRefreshToken(String token) {
        return getRefreshClaims(token).getSubject();
    }

    public String getAuthorityLevelFromToken(String token) {
        return getAccessClaims(token).get("authorityLevel", String.class);
    }

}
