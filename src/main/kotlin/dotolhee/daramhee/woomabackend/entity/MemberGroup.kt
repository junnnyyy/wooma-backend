package dotolhee.daramhee.woomabackend.entity

import jakarta.persistence.*

@Entity
class MemberGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long = 0L,

    @OneToMany(mappedBy = "memberGroup")
    val members: List<Member> = mutableListOf(),
): BaseEntity()