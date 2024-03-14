package dotolhee.daramhee.woomabackend.repository

import dotolhee.daramhee.woomabackend.entity.Member
import dotolhee.daramhee.woomabackend.entity.MemberGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberGroupRepository : JpaRepository<MemberGroup, Long> {
    fun findByMembers(members: MutableList<Member>): MemberGroup?
}