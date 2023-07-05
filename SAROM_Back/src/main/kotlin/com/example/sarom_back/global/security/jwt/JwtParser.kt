package com.example.sarom_back.global.security.jwt

import com.example.sarom_back.domain.auth.type.Authority
import com.example.sarom_back.global.security.principle.ParentDetailsService
import com.example.sarom_back.global.security.principle.StudentDetailsService
import com.example.sarom_back.global.security.properties.SecurityProperties
import com.prject.nextstep.global.exception.ExpiredTokenException
import com.prject.nextstep.global.exception.InternalServerErrorException
import com.prject.nextstep.global.exception.InvalidRoleException
import com.prject.nextstep.global.exception.InvalidTokenException
import com.prject.nextstep.global.exception.UnexpectedTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class JwtParser (
    private val securityProperties: SecurityProperties,
    private val parentDetailsService: ParentDetailsService,
    private val studentDetailsService: StudentDetailsService
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtProvider.ACCESS) {
            throw InvalidTokenException
        }

        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(securityProperties.key).build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw UnexpectedTokenException
                else -> throw InternalServerErrorException
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {

        return when (body.get("authority", Authority::class.java)) {
            Authority.PARENT -> parentDetailsService.loadUserByUsername(body.id)
            Authority.STUDENT -> studentDetailsService.loadUserByUsername(body.id)
            else -> throw InvalidRoleException
        }
    }
}