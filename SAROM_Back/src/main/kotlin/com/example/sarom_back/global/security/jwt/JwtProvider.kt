package com.example.sarom_back.global.security.jwt

import com.example.sarom_back.domain.auth.TokenResponse
import com.example.sarom_back.domain.auth.entity.RefreshTokenEntity
import com.example.sarom_back.domain.auth.entity.RefreshTokenRepository
import com.example.sarom_back.domain.auth.type.Authority
import com.example.sarom_back.global.security.properties.SecurityProperties
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date

@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    companion object {
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val AUTHORITY = "authority"
    }

    fun receiveToken(userId: Int, authority: Authority) = TokenResponse(
        accessToken = generateAccessToken(userId, authority),
        expiredAt = LocalDateTime.now().plusSeconds(securityProperties.accessExp.toLong()),
        refreshToken = generateRefreshToken(userId, authority),
        authority = authority
    )

    private fun generateAccessToken(userId: Int, authority: Authority) =
        Jwts.builder()
            .signWith(securityProperties.key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.JWT_TYPE, ACCESS)
            .setId(userId.toString())
            .claim(AUTHORITY, authority.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp))
            .compact()

    private fun generateRefreshToken(userId: Int, authority: Authority): String {
        val token = Jwts.builder()
            .signWith(securityProperties.key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.JWT_TYPE, REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExp))
            .compact()

        val refreshToken = RefreshTokenEntity(
            token = token,
            userId = userId,
            authority = authority,
            expirationTime = securityProperties.refreshExp / 1000
        )
        refreshTokenRepository.save(refreshToken)

        return token
    }
}