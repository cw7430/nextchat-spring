package com.next.chat.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`social_auth`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SocialAuth {
    @Id
    @Column(name = "`social_auth_id`", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialAuthId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_idx`", nullable = false)
    private User user;

    @Column(
            name = "`platform`",
            nullable = false,
            columnDefinition = "VARCHAR(100) CHECK (`platform` IN ('NAVER', 'KAKAO', 'GOOGLE'))"
    )
    private String platform;

    @Column(name = "`provider_user_id`", nullable = false)
    private String providerUserId;
}
