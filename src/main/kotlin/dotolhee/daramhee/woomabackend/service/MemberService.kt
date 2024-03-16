package dotolhee.daramhee.woomabackend.service

import dotolhee.daramhee.woomabackend.dto.MemberDTO
import dotolhee.daramhee.woomabackend.entity.Member
import dotolhee.daramhee.woomabackend.repository.MemberRepository
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val messageSource: MessageSource,
) {
    
    @Transactional
    fun register(dto: MemberDTO.RegisterRequestDTO): MemberDTO.RegisterResponseDTO {
        emailValidation(dto.email)
        usernameValidation(dto.username)
        val passwordEncoder = BCryptPasswordEncoder(16)
        val encryptedPassword = passwordEncoder.encode(dto.password)
        val newMember = memberRepository.save(Member(dto, encryptedPassword))
        return MemberDTO.RegisterResponseDTO(newMember.username, "")
    }
    
    fun authenticate() {}
    
    private fun emailValidation(email: String): Boolean {
        if (memberRepository.findByEmail(email) != null) {
            throw IllegalArgumentException(
                messageSource.getMessage(
                    "ERROR.MEMBER.ALREADY_EXISTED_EMAIL",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }
        return true
    }
    
    private fun usernameValidation(username: String): Boolean {
        if (memberRepository.findByUsername(username) != null) {
            throw IllegalArgumentException(
                messageSource.getMessage(
                    "ERROR.MEMBER.INVALID_USERNAME",
                    null,
                    LocaleContextHolder.getLocale()
                )
            )
        }
        return true
    }
}