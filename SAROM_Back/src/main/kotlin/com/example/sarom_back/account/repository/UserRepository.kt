package com.example.sarom_back.account.repository

import com.example.sarom_back.account.entity.UserEntity
import org.springframework.web.bind.annotation.PostMapping

class UserRepository {
    private val users: MutableList<UserEntity> = mutableListOf()

    private fun generateId(): Long {
        return System.currentTimeMillis()
    }

    fun save(userEntity: UserEntity): UserEntity {
        userEntity.id = generateId()
        users.add(userEntity)
        return userEntity
    }

    fun findByUid(uid: String): UserEntity? {
        return users.find { it.uid == uid }
    }
}