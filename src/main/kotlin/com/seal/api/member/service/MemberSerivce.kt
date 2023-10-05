package com.seal.api.member.service

import com.seal.api.member.dto.MemberDtoRequest
import com.seal.api.member.entity.Member
import com.seal.api.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberSerivce (
    private val memberRepository: MemberRepository
){
    // 회원가입
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        // 아이디 중복검사
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if(member != null){
            return "이미 등록된 ID입니다."
        }

        member = Member(
            memberDtoRequest.id,
            memberDtoRequest.loginId,
            memberDtoRequest.password,
            memberDtoRequest.name,
            memberDtoRequest.birthDate,
            memberDtoRequest.gender,
            memberDtoRequest.email
        )

        memberRepository.save(member)

        return "회원가입이 완료되었습니다."
    }
}