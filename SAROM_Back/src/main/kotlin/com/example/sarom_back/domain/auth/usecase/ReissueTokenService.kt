package com.example.sarom_back.domain.auth.usecase

import com.example.sarom_back.domain.auth.TokenResponse
import com.example.sarom_back.domain.auth.entity.RefreshTokenRepository
import com.example.sarom_back.global.security.jwt.JwtProvider
import org.springframework.stereotype.Service

@Service
class ReissueTokenService(
    private val jwtProvider: JwtProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(refreshToken: String): TokenResponse {
        val refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken) ?: throw TODO("notfound exceitpion")

        return jwtProvider.receiveToken(refreshTokenEntity.userId, refreshTokenEntity.authority)
    }
}