package com.seal.api.member.entity

import com.seal.api.common.status.Gender
import jakarta.persistence.*
import jakarta.persistence.criteria.CriteriaBuilder.Case
import java.time.LocalDate
import kotlin.jvm.Transient
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass.Factory

@Entity
@Table(
        name = "member",
        uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])]
)
data class MemberEntity (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var mId: Int? = null){
    @Column(nullable = false, length = 30, updatable = false)   var loginId:    String?     = null
    @Column(nullable = false, length = 100)                     var password:   String?     = null
    @Column(nullable = false, length = 10)                      var name:       String?     = null
    @Column(nullable = false) @Temporal(TemporalType.DATE)      var birthDate:  LocalDate?  = null
    @Column(nullable = false) @Enumerated(EnumType.STRING)      var gender:     Gender?     = null
    @Column(nullable = false, length = 30)                      var email:      String?     = null

    @OneToMany(mappedBy = "members", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var roles:MutableList<MemberRoleEntity>? = null
}