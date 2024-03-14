//package dotolhee.daramhee.woomabackend.advice
//
//import dotolhee.daramhee.woomabackend.dto.MemberDTO
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.core.MethodParameter
//import org.springframework.http.MediaType
//import org.springframework.http.converter.HttpMessageConverter
//import org.springframework.http.server.ServerHttpRequest
//import org.springframework.http.server.ServerHttpResponse
//import org.springframework.web.bind.annotation.RestControllerAdvice
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
//
//@RestControllerAdvice
//class CustomResponseAdvice: ResponseBodyAdvice<Any> {
//    val logger: Logger = LoggerFactory.getLogger(CustomResponseAdvice::class.java)
//    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun beforeBodyWrite(
//        body: Any?,
//        returnType: MethodParameter,
//        selectedContentType: MediaType,
//        selectedConverterType: Class<out HttpMessageConverter<*>>,
//        request: ServerHttpRequest,
//        response: ServerHttpResponse
//    ): Any? {
//        return if (body is MemberDTO.RegisterResponseDTO){
//            body
//        } else true
//    }
//}