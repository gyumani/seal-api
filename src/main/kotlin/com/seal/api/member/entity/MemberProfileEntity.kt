package com.seal.api.member.entity

import jakarta.persistence.*

@Entity
@Table(
        name = "member_profile",
        uniqueConstraints = [UniqueConstraint(name="uk_member_profile_nick_name", columnNames = ["nickName"])]
)
data class MemberProfileEntity (@Id @GeneratedValue(strategy =  GenerationType.IDENTITY) var pId: Int? = null){
    @Column(nullable = false, length = 50)  var nickName:   String?         = null
    @Column(nullable = false, length = 200) var content:    String?         = null

    @ManyToOne @JoinColumn(name = "mId")    var members:    MemberEntity?   = null
}