package dotolhee.daramhee.woomabackend.dto

class MemberDTO {
    data class RegisterRequestDTO(
        val username: String,
        val password: String,
        val email: String,
        val name: String,
    )
    data class RegisterResponseDTO(
        val username: String,
        val token: String,
    )
}