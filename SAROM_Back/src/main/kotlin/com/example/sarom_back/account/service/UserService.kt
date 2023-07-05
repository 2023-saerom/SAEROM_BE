package com.example.sarom_back.account.service

import com.example.sarom_back.account.dto.UserDto
import com.example.sarom_back.account.entity.UserEntity
import com.example.sarom_back.account.repository.UserRepository
import org.springframework.web.bind.annotation.PostMapping

class UserService(private val userRepository: UserRepository) {
    @PostMapping("/signup")
    fun save(userDto: UserDto) {
        val userEntity = UserEntity().apply {
            this.name = userDto.name
            this.uid = userDto.uid
            this.pwd = userDto.pwd
            this.email = userDto.email
        }
        userRepository.save(userEntity)
    }

    @PostMapping("/login")
    fun login(userDto: UserDto): UserDto? {
        val userEntity: UserEntity? = userDto.name?.let { userRepository.findByUid(it) }
        if (userEntity != null && userEntity.pwd == userDto.pwd) {
            return UserDto(userEntity.id, userEntity.name, userEntity.pwd)
        }
        return null
    }

    fun userExists(uid: String): Boolean {
        return userRepository.findByUid(uid) != null
    }
}