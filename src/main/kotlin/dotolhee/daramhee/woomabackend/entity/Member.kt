package dotolhee.daramhee.woomabackend.entity

import dotolhee.daramhee.woomabackend.OAuthProvider
import dotolhee.daramhee.woomabackend.dto.MemberDTO
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
    var token: String? = null,

    @Column
    var refreshToken: String? = null,

    @Column
    var tokenExpiredAt: LocalDateTime? = null,

    @Column
    var refreshTokenExpiredAt: LocalDateTime? = null,

    @Column
    val lastSignInAt: LocalDateTime? = null,

    @Column
    val oAuthProvider: OAuthProvider? = null,

    @Column
    val deletedAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_group_id")
    val memberGroup: MemberGroup? = null,
) : BaseEntity() {
    constructor(
        dto: MemberDTO.RegisterRequestDTO,
        encryptedPassword: String
    ) : this(
        id = 0L,
        username = dto.username,
        encryptedPassword = encryptedPassword,
        email = dto.email,
        name = dto.name
    )
}