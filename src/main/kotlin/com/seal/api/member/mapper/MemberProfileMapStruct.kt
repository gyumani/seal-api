package com.seal.api.member.mapper

import com.seal.api.common.mapstruct.EntityMapper
import com.seal.api.member.dto.MemberProfileDTO
import com.seal.api.member.entity.MemberProfileEntity
import org.aspectj.lang.annotation.After
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface MemberProfileMapStruct: EntityMapper<MemberProfileEntity, MemberProfileDTO> {

    @Mappings(
    )
    override fun toEntity(dto: MemberProfileDTO): MemberProfileEntity

    @InheritConfiguration
    override fun toDTO(entity: MemberProfileEntity): MemberProfileDTO

    companion object {
        @JvmStatic @AfterMapping
        fun afterMapping(dto: MemberProfileDTO, @MappingTarget entity: MemberProfileEntity) {
        }
    }
}