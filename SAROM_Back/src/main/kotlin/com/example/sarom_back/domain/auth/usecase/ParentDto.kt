package com.example.sarom_back.domain.auth.usecase

import com.example.sarom_back.domain.parent.entity.ParentEntity

class ParentDto(
    var id: Int? = null,
    var username: String? = null,
    var nickname: String? = null,
    var password: String? = null,
    var email: String? = null
) {
    companion object {
        fun toParentDto(parentEntity: ParentEntity): ParentDto {
            return ParentDto(
                id = parentEntity.id,
                username = parentEntity.username,
                nickname = parentEntity.nickname,
                password = parentEntity.password,
                email = parentEntity.email
            )
        }
    }
}