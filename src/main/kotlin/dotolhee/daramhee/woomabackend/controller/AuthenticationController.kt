package dotolhee.daramhee.woomabackend.controller

import dotolhee.daramhee.woomabackend.dto.MemberDTO
import dotolhee.daramhee.woomabackend.service.MemberService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="회원")
@RequestMapping(value = ["/auth"])
@RestController
class AuthenticationController(
    private val memberService: MemberService
) {

    @PostMapping("/register")
    fun register(dto: MemberDTO.RegisterRequestDTO): MemberDTO.RegisterResponseDTO {
        val member = memberService.register(dto)
        return member
    }
}