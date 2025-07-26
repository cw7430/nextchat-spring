package com.next.chat.auth.repository;

import com.next.chat.auth.entity.SocialAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAuthRepository extends JpaRepository<SocialAuth, Long> {
}
