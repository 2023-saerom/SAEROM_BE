package com.example.sarom_back.domain.auth.presentation.dto.response

import org.jetbrains.annotations.NotNull

data class SignInRequest(
    @field:NotNull
    val username: String,

    val password: String
)
