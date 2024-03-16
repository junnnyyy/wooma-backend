package dotolhee.daramhee.woomabackend.dto

import java.time.LocalDate

class MemberDTO {
    data class RegisterRequestDTO(
        val username: String,
        val password: String,
        val email: String,
        val name: String,
        val birthDate: LocalDate?,
    )
    data class RegisterResponseDTO(
        val username: String,
        val token: String,
    )

    data class AuthenticateRequestDTO(
        val email: String,
        val password: String,
    )
//    data class AuthenticateResponseDTO()
}