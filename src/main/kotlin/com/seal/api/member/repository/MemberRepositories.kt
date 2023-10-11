package com.seal.api.member.repository

import com.seal.api.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Int> {
    fun findByLoginId(loginId: String): MemberEntity?
}