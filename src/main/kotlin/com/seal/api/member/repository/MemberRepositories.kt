package com.seal.api.member.repository

import com.seal.api.member.dto.MemberProfileDTO
import com.seal.api.member.entity.MemberEntity
import com.seal.api.member.entity.MemberProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Int> {
    fun findByLoginId(loginId: String): MemberEntity?
}

interface MemberProfileRepository: JpaRepository<MemberProfileEntity, Int> {
    fun findListByMembers(memberEntity: MemberEntity): MutableList<MemberProfileEntity>?
}