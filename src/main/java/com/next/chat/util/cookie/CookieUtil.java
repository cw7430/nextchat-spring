package com.next.chat.util.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void addCookie(HttpServletResponse response, String name, String value, boolean isHttpOnly, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String name, boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 삭제
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);
    }
}
