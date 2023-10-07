package com.seal.api.member.controller

import com.fasterxml.jackson.databind.ser.Serializers.Base
import com.seal.api.common.authority.TokenInfo
import com.seal.api.common.dto.BaseResponse
import com.seal.api.common.dto.CustomUser
import com.seal.api.member.dto.LoginDto
import com.seal.api.member.dto.MemberDtoRequest
import com.seal.api.member.dto.MemberDtoResponse
import com.seal.api.member.service.MemberSerivce
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        return BaseResponse(message = memberSerivce.signUp(memberDtoRequest))
    }

    /*
    * 로그인
    * */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        return BaseResponse(data = memberSerivce.login(loginDto))
    }

    /**
     * 내 정보 보기
     */
    @GetMapping("/info")
    fun searchMyInfo() : BaseResponse<MemberDtoResponse> {
        val userId = (
                SecurityContextHolder
                    .getContext()
                    .authentication.principal as CustomUser
                ).userId
        return BaseResponse(data = memberSerivce.searchMyInfo(userId))
    }

}