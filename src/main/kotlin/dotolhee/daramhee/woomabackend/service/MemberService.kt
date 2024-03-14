package dotolhee.daramhee.woomabackend.service

import dotolhee.daramhee.woomabackend.dto.MemberDTO
import dotolhee.daramhee.woomabackend.entity.Member
import dotolhee.daramhee.woomabackend.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
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

    private fun emailValidation(email: String): Boolean {
        if (memberRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("이미 가입된 이메일 입니다.")
        }
        return true
    }

    private fun usernameValidation(username: String): Boolean {
        if (memberRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("이미 사용 중인 아이디 입니다.")
        }
        return true
    }
}