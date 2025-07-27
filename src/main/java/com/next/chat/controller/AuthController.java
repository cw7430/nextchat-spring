package com.next.chat.controller;

import com.next.chat.dto.request.auth.NativeSignInRequestDto;
import com.next.chat.dto.response.ResponseDto;
import com.next.chat.dto.response.auth.SignInResponseDto;
import com.next.chat.service.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign_in_native")
    public ResponseDto<SignInResponseDto> signInNative(HttpServletResponse response, @Valid @RequestBody NativeSignInRequestDto nativeSignInRequestDto) {
        return ResponseDto.success(authService.signInNative(response, nativeSignInRequestDto));
    }
}
