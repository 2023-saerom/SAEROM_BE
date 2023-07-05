package com.example.sarom_back.global.security.principle

import com.example.sarom_back.domain.parent.entity.ParentRepository
import com.prject.nextstep.global.exception.InvalidTokenException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component

class ParentDetailsService(
    private val parentRepository: ParentRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val parent = parentRepository.findByUsername(username!!) ?: throw InvalidTokenException

        return ParentDetails(parent.id)
    }
}