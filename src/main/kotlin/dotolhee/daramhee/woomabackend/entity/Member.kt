package dotolhee.daramhee.woomabackend.entity

import dotolhee.daramhee.woomabackend.OAuthProvider
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L,

    @Column
    val username: String,

    @Column
    val encryptedPassword: String,

    @Column
    val email: String,

    @Column
    val name: String,

    @Column
    var token: String?,

    @Column
    var refreshToken: String?,

    @Column
    var tokenExpiredAt: LocalDateTime?,

    @Column
    var refreshTokenExpiredAt: LocalDateTime?,

    @Column
    val lastSignInAt: LocalDateTime?,

    @Column
    val oAuthProvider: OAuthProvider?,

    @Column
    val deletedAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_id")
    val memberGroup: MemberGroup?,
): BaseEntity()