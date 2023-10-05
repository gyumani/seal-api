package com.seal.api.member.controller

import com.seal.api.common.dto.BaseResponse
import com.seal.api.member.dto.MemberDtoRequest
import com.seal.api.member.service.MemberSerivce
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/member")
@RestController
class MemberController (
    private val memberSerivce: MemberSerivce
){
    //회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberSerivce.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
}