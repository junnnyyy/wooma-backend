package dotolhee.daramhee.woomabackend.dto

class KakaoOAuthDTO {

    data class LoginRequestDTO(
        val client_id: String,
        val redirect_uri: String,
        val response_type: String,
        val scope: String?,
        val prompt: String?,
        val login_hint: String?,
        val service_terms: String,
        val state: String?,
        val nonce: String?,
    )

    data class LoginResponseDTO(
        val code: String?,
        val error: String?,
        val error_description: String?,
        val state: String?
    )

    data class TokenRequestDTO(
        val grant_type: String,
        val client_id: String,
        val redirect_uri: String,
        val code: String,
        val client_secret: String?,
    )

    data class TokenResponseDTO(
        val token_type: String,
        val access_token: String,
        val id_token: String?,
        val expires_in: String,
        val refresh_token: String,
        val refresh_token_expires_in: String,
        val scope: String?,
    )
}