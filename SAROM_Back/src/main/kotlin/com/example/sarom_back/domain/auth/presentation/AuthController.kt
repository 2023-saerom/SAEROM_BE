package com.example.sarom_back.domain.auth.presentation

import com.example.sarom_back.domain.auth.TokenResponse
import com.example.sarom_back.domain.auth.presentation.dto.response.SignInRequest
import com.example.sarom_back.domain.auth.usecase.SignInService
import com.example.sarom_back.domain.auth.usecase.ReissueTokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val signInService: SignInService,
    private val reissueTokenService: ReissueTokenService
) {

    @PostMapping("/signing")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<TokenResponse> {
        val tokenResponse = signInService.execute(request)
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping("/reissue-token")
    fun reissueToken(@RequestBody refreshToken: String): ResponseEntity<TokenResponse> {
        val tokenResponse = reissueTokenService.execute(refreshToken)
        return ResponseEntity.ok(tokenResponse)
    }
}
