package com.seal.api.member.service

import com.seal.api.common.exception.InvalidInputException
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
            throw InvalidInputException("loginId", "이미 등록된 ID입니다.")
        }

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        return "회원가입이 완료되었습니다."
    }
}