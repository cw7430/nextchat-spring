package com.next.chat.auth.service;

import com.next.chat.auth.mapper.AuthMapper;
import com.next.chat.auth.repository.NativeAuthRepository;
import com.next.chat.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final NativeAuthRepository nativeAuthRepository;
    private final AuthMapper authMapper;
}
