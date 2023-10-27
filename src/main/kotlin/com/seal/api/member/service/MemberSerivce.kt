package com.seal.api.member.service

import com.seal.api.common.authority.JwtTokenProvider
import com.seal.api.common.authority.TokenInfo
import com.seal.api.common.dto.CustomUser
import com.seal.api.common.exception.InvalidInputException
import com.seal.api.member.dto.LoginDTO
import com.seal.api.member.dto.MemberDTO
import com.seal.api.member.dto.MemberProfileDTO
import com.seal.api.member.entity.MemberEntity
import com.seal.api.member.mapper.MemberMapStruct
import com.seal.api.member.mapper.MemberProfileMapStruct
import com.seal.api.member.repository.MemberProfileRepository
import com.seal.api.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class MemberSerivce (
    private val memberRepository                : MemberRepository,
    private val memberProfileRepository         : MemberProfileRepository,
    private val authenticationManagerBuilder    : AuthenticationManagerBuilder,
    private val jwtTokenProvider                : JwtTokenProvider,
){

    @Autowired private lateinit var memberMapStruct: MemberMapStruct
    @Autowired private lateinit var memberProfileMapStatic: MemberProfileMapStruct

    // 회원가입
    @Transactional
    fun signUp(memberDTO: MemberDTO): String {
        // 아이디 중복검사
        var member: MemberEntity? = memberDTO.loginId?.let { memberRepository.findByLoginId(it) }
        if(member != null){
            throw InvalidInputException("loginId", "이미 등록된 ID입니다.")
        }

        // 권한 저장
        member = memberMapStruct.toEntity(memberDTO)
        memberRepository.save(member)


        return "회원가입이 완료되었습니다."
    }

    /*
    * 로그인
    * */
    fun login(loginDto: LoginDTO): TokenInfo {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication =
            authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    /*
    *  내 정보 보기
    * */
    fun searchMyInfo(id: Int): MemberDTO {
        val member = memberRepository.findByIdOrNull(id)
            ?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")

        return memberMapStruct.toDTO(member)
    }

    /**
     * 내 정보 수정
     */
    @Transactional
    fun saveMyInfo(memberDTO: MemberDTO): String {
        val member = memberMapStruct.toEntity(memberDTO)
        memberRepository.save(member)
        return "수정 완료 되었습니다."
    }


    fun profileList(id: Int) : MutableList<MemberProfileDTO> {
        val member = memberInfo(id)

        var profiles: MutableList<MemberProfileDTO> = mutableListOf()
        var profilesEntity = memberProfileRepository.findListByMembers(member)
                ?: throw InvalidInputException("id", "프로필이 존재하지 않습니다.")

        profilesEntity?.forEach {
            profiles.add(memberProfileMapStatic.toDTO(it))
        }

        return profiles
    }


    @Transactional
    fun profileSave(profileDTO: MemberProfileDTO, message: String): String {
        println(profileDTO.pId)
        val profile = memberProfileMapStatic.toEntity(profileDTO)
        val member = memberInfo(
                (SecurityContextHolder.getContext().authentication.principal as CustomUser).mId
        )

        println(profile.pId)

        profile.members = member

        memberProfileRepository.save(profile)
        return message
    }


    private fun memberInfo(id: Int) : MemberEntity{
        return memberRepository.findByIdOrNull(id)
                ?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
    }

}