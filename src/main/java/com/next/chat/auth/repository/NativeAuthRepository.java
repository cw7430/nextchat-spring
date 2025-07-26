package com.next.chat.auth.repository;

import com.next.chat.auth.entity.NativeAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NativeAuthRepository extends JpaRepository<NativeAuth, Long> {
}
