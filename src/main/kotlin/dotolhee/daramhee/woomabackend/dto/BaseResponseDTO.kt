package dotolhee.daramhee.woomabackend.dto

class BaseResponseDTO<T>(
    val success: Boolean,
    val message: String = "",
    val data: T? = null,
) {
    companion object {
        fun <T> success(data: T, message: String = "성공"): BaseResponseDTO<T> =
            BaseResponseDTO(success = true, message = message, data = data)
        
        fun error(errMessage: String = ""): BaseResponseDTO<Any> =
            BaseResponseDTO(success = false, message = errMessage)
    }
}