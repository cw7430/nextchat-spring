package com.next.chat.repository.auth;

import com.next.chat.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
