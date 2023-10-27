package com.seal.api.member.controller

import com.seal.api.common.authority.TokenInfo
import com.seal.api.common.dto.BaseResponse
import com.seal.api.common.dto.CustomUser
import com.seal.api.member.dto.LoginDTO
import com.seal.api.member.dto.MemberDTO
import com.seal.api.member.dto.MemberProfileDTO
import com.seal.api.member.service.MemberSerivce
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController (
    private val memberSerivce: MemberSerivce
){
    //회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDTO: MemberDTO): BaseResponse<Unit> {
        return BaseResponse(message = memberSerivce.signUp(memberDTO))
    }

    /*
     * 로그인
     * */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDTO): BaseResponse<TokenInfo> {
        return BaseResponse(data = memberSerivce.login(loginDto))
    }
//
    /**
     * 내 정보 보기
     */
    @GetMapping("/info")
    fun searchMyInfo() : BaseResponse<MemberDTO> {
        val mId = (
                SecurityContextHolder
                    .getContext()
                    .authentication
                    .principal as CustomUser
                ).mId
        return BaseResponse(data = memberSerivce.searchMyInfo(mId))
    }

    /**
     * 내 정보 수정
     */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDTO: MemberDTO):
            BaseResponse<Unit> {
        memberDTO.mId = (
                SecurityContextHolder
                    .getContext()
                    .authentication
                    .principal as CustomUser
                ).mId

        return BaseResponse(message = memberSerivce.saveMyInfo(memberDTO))
    }


    @PostMapping("/profile/save")
    fun profileSave(@RequestBody @Valid profileDTO: MemberProfileDTO):BaseResponse<Unit> {
        return BaseResponse(message = memberSerivce.profileSave(profileDTO, "신규 프로파일이 추가되었습니다."))
    }

    @PutMapping("/profile/save")
    fun profileModify(@RequestBody @Valid profileDTO: MemberProfileDTO):BaseResponse<Unit> {
        return BaseResponse(message = memberSerivce.profileSave(profileDTO, "프로파일이 수정되었습니다."))
    }
    @GetMapping("/profile/list")
    fun profileList() : BaseResponse<MutableList<MemberProfileDTO>> {
        val mId = (
                SecurityContextHolder.getContext().authentication.principal as CustomUser
                ).mId

        return BaseResponse(data = memberSerivce.profileList(mId))
    }


}