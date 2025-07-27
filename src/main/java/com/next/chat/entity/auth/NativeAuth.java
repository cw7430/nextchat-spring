package com.next.chat.entity.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "`native_auths`")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NativeAuth {

    @Id
    @Column(name = "`native_auth_id`", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nativeAuthId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_idx`", referencedColumnName = "`user_idx`", nullable = false)
    private User user;

    @Column(name = "`password`", nullable = false, length = 100)
    private String password;
}
