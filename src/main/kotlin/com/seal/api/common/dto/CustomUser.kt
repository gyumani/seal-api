package com.seal.api.common.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser (
    val mId: Int,
    userName: String,
    password: String,
    authorities: Collection<GrantedAuthority>
): User(userName, password, authorities)