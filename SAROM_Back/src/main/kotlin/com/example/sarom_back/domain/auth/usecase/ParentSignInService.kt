package com.example.sarom_back.domain.auth.usecase

import com.example.sarom_back.domain.auth.TokenResponse
import com.example.sarom_back.domain.auth.type.Authority
import com.example.sarom_back.domain.parent.entity.ParentRepository
import com.example.sarom_back.global.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInService(
    private val jwtProvider: JwtProvider,
    private val parentRepository: ParentRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun execute(request: SignInRequest): TokenResponse {

        val parent = parentRepository.findByUsername(request.username) ?: TODO("Parent Not Fonund Exception")

        if (!passwordEncoder.matches(request.password, parent.password)) TODO("Invalidpass")

        return jwtProvider.receiveToken(parent.id, Authority.PARENT)
    }

}