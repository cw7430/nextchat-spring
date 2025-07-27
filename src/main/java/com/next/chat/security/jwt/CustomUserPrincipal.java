package com.next.chat.security.jwt;

public record CustomUserPrincipal(String userId, String authorityCode) {
}
