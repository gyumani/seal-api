package com.seal.api.member.controller

import com.seal.api.member.dto.MemberDtoRequest
import com.seal.api.member.service.MemberSerivce
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
    fun signUp(@RequestBody memberDtoRequest: MemberDtoRequest): String {
        return memberSerivce.signUp(memberDtoRequest)
    }
}