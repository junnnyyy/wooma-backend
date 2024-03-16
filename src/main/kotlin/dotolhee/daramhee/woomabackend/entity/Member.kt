package dotolhee.daramhee.woomabackend.entity

import dotolhee.daramhee.woomabackend.OAuthProvider
import dotolhee.daramhee.woomabackend.dto.MemberDTO
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate
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
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @Column
    val name: String,

    @Column
    val birthDate: LocalDate?,

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
        name = dto.name,
        birthDate = dto.birthDate,
    )
}