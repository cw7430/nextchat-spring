package com.next.chat.auth.dto.service;

public record TokenServiceDto(String token, long expiresAt) {
}
