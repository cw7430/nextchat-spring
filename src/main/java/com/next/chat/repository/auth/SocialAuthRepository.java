package com.next.chat.repository.auth;

import com.next.chat.entity.auth.SocialAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAuthRepository extends JpaRepository<SocialAuth, Long> {
}
