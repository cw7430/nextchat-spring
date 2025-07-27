package com.next.chat.dto.service.auth;

public record TokenServiceDto(String token, long expiresAt) {
}
