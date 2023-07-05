package com.example.sarom_back.domain.auth

import com.example.sarom_back.domain.auth.type.Authority
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val expiredAt: LocalDateTime,
    val refreshToken: String,
    val authority: Authority?
)
