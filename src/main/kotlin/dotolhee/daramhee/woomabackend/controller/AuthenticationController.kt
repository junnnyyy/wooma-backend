package dotolhee.daramhee.woomabackend.controller

import dotolhee.daramhee.woomabackend.dto.BaseResponseDTO
import dotolhee.daramhee.woomabackend.dto.MemberDTO
import dotolhee.daramhee.woomabackend.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원", description = "회원 API")
@RestController
@RequestMapping(value = ["/auth"])
class AuthenticationController(
    private val memberService: MemberService
) {

    @Operation(summary = "회원가입 요청")
    @PostMapping("/register")
    fun register(
        @Valid
        @RequestBody
        dto: MemberDTO.RegisterRequestDTO
    ): MemberDTO.RegisterResponseDTO {
        val member: MemberDTO.RegisterResponseDTO = memberService.register(dto)
        return member
    }
}