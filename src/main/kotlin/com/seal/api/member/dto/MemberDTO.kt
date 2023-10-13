package com.seal.api.member.dto

import com.seal.api.common.status.Gender
import com.seal.api.member.entity.MemberProfileEntity
import com.seal.api.member.entity.MemberRoleEntity
import jakarta.persistence.*
import java.time.LocalDate

data class MemberDTO(var mId:Int? = null) {
    var loginId:    String?     = null
    var password:   String?     = null
    var name:       String?     = null
    var birthDate:  LocalDate?  = null
    var gender:     Gender?     = null
    var email:      String?     = null

    val roles:      MutableList<MemberRoleEntity>?   = null
    val profiles:   MutableList<MemberProfileDTO>?   = null
}
