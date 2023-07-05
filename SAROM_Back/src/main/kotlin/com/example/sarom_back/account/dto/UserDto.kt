package com.example.sarom_back.account.dto

import com.example.sarom_back.account.entity.UserEntity

data class UserDto(
    var id: Long? = null,
    var name: String? = null,
    var uid: String? = null,
    var pwd: String? = null,
    var email: String? = null
) {
    companion object {
        fun toUserDto(userEntity: UserEntity): UserDto {
            return UserDto(
                id = userEntity.id,
                name = userEntity.name,
                uid = userEntity.uid,
                pwd = userEntity.pwd,
                email = userEntity.email
            )
        }
    }
}