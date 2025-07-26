package com.next.chat.entities.auth;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "`users`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "`user_idx`", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(name = "`user_id`", unique = true, nullable = false, length = 255)
    private String userId;

    // 1 - 일반회원, 2 - 관리자, 0 - 탈퇴자
    @Builder.Default
    @Column(
            name = "`authority_level`",
            nullable = false,
            length = 100,
            columnDefinition = "VARCHAR(100) CHECK (`authority_level` IN ('0', '1', '2')) DEFAULT '1'"
    )
    private String authorityLevel = "1";

    // NATIVE - 가입회원, SOCIAL - Oauth 회원, CROSS - 복합회원(가입 + 소셜계정연동)
    @Column(
            name = "`auth_method`",
            nullable = false,
            length = 100,
            columnDefinition = "VARCHAR(100) CHECK (`auth_method` IN ('NATIVE', 'SOCIAL', 'CROSS'))"
    )
    private String authMethod;

    @Column(
            name = "`created_at`",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private Instant createdAt;

    @Column(
            name = "`updated_at`",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private Instant updatedAt;

    @Builder.Default
    @Column(name = "`is_valid`", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean valid = true;

    // Hibernate 동기화용: 생성 시
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    // Hibernate 동기화용: 수정 시
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

}
