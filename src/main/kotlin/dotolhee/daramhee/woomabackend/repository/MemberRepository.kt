package dotolhee.daramhee.woomabackend.repository

import dotolhee.daramhee.woomabackend.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
    fun findByUsername(username: String): Member?
}