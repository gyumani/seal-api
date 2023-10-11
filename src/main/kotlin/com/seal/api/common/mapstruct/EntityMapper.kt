package com.seal.api.common.mapstruct

interface EntityMapper <E, D> {
    fun toDTO(entity:E): D
    fun toEntity(dto:D): E
}