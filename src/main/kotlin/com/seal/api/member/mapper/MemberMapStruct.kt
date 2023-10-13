package com.seal.api.member.mapper

import com.seal.api.common.mapstruct.EntityMapper
import com.seal.api.common.status.ROLE
import com.seal.api.member.dto.MemberDTO
import com.seal.api.member.entity.MemberEntity
import com.seal.api.member.entity.MemberRoleEntity
import org.mapstruct.*
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring", uses=[MemberProfileMapStruct::class]) @Component
interface MemberMapStruct: EntityMapper<MemberEntity, MemberDTO> {

    @Mappings(
    )
    override fun toEntity(dto: MemberDTO): MemberEntity
    @InheritInverseConfiguration
    override fun toDTO(entity: MemberEntity): MemberDTO

    companion object {
        @JvmStatic @AfterMapping
        fun afterMapping (dto: MemberDTO,@MappingTarget entity: MemberEntity) {
            entity.profiles?.forEach {
                println(it.nickName)
            }
            if(entity.roles == null) {
                var memberRole = MemberRoleEntity(mrId = entity.mId)
                memberRole.role = ROLE.MEMBER
                memberRole.members = entity
                entity.roles = mutableListOf(memberRole)
            }

            entity.profiles?.forEach { it.members = entity }
        }
    }

}