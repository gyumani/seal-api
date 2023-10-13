package com.seal.api.member.dto

import com.seal.api.member.entity.MemberEntity

data class MemberProfileDTO (var pId:Int? = null){
    var nickName:   String?         = null
    var content:    String?         = null
    var members:    MemberEntity?   = null
}