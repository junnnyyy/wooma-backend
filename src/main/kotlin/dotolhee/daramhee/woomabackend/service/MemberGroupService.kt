package dotolhee.daramhee.woomabackend.service

import dotolhee.daramhee.woomabackend.entity.Member
import dotolhee.daramhee.woomabackend.entity.MemberGroup
import dotolhee.daramhee.woomabackend.repository.MemberGroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberGroupService(
    private val memberGroupRepository: MemberGroupRepository
) {

    @Transactional
    fun createMemberGroup(member: Member): MemberGroup? {
        return memberGroupRepository.findByMembers(mutableListOf(member))?.let {
            null
        } ?: memberGroupRepository.save(
            MemberGroup(
                id = 0L,
                members = listOf(member)
            )
        )
    }
}