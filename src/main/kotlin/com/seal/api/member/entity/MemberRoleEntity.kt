package com.seal.api.member.entity

import com.seal.api.common.status.ROLE
import jakarta.persistence.*

@Entity
@Table(
        name = "member_role"
)
data class MemberRoleEntity(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var mrId: Int? = null) {
    @Column(nullable = false, length = 30) @Enumerated(EnumType.STRING) var role: ROLE? = null
    @ManyToOne @JoinColumn(name="mId") var members: MemberEntity? = null

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(foreignKey = ForeignKey(name="fk_user_role_member_id"))
//    val member: Member

}
