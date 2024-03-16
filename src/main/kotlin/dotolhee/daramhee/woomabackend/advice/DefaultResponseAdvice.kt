package dotolhee.daramhee.woomabackend.advice

import dotolhee.daramhee.woomabackend.dto.BaseResponseDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.TypeMismatchException
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class DefaultResponseAdvice : ResponseBodyAdvice<Any> {
    
    val logger: Logger = LoggerFactory.getLogger(DefaultResponseAdvice::class.java)
    
    /*
    * return true
    * -> beforeBodyWrite 호출
    * */
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }
    
    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse,
    ): Any? {
        /*
        * Openapi 문서인 경우 그대로 리턴
        * */
        if (returnType.method?.name.equals("openapiJson")) return body
        
        return when (body) {
            is BaseResponseDTO<*> -> body
            /*Page 객체에 대한 처리 필요*/
            else -> BaseResponseDTO.success(data = body)
        }
    }
    
    @ExceptionHandler(value =
        [TypeMismatchException::class,
        IllegalArgumentException::class,
        HttpMessageNotReadableException::class,]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException400(ex: Exception): BaseResponseDTO<Any> {
        logger.error(ex.printStackTrace().toString())
        return BaseResponseDTO.error(
            errMessage = ex.message ?: "",
        )
    }

//    @ExceptionHandler(
//
//    )
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    fun handleException401(ex: Exception): BaseResponseDTO<Any> {
//        logger.error(ex.printStackTrace().toString())
//        return BaseResponseDTO.error(
//            errMessage = ex.message ?: "",
//        )
//    }
    
    @ExceptionHandler(
        NoSuchElementException::class,
        NoSuchFileException::class,
        NoHandlerFoundException::class,
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException404(ex: Exception): BaseResponseDTO<Any> {
        logger.error(ex.printStackTrace().toString())
        return BaseResponseDTO.error(
            errMessage = ex.message ?: "",
        )
    }
    
    @ExceptionHandler(
        Exception::class,
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException500(ex: Exception): BaseResponseDTO<Any> {
        logger.error(ex.printStackTrace().toString())
        return BaseResponseDTO.error(
            errMessage = ex.message ?: "",
        )
    }
}