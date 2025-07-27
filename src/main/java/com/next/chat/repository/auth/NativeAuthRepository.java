package com.next.chat.repository.auth;

import com.next.chat.entity.auth.NativeAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NativeAuthRepository extends JpaRepository<NativeAuth, Long> {
}
