package com.example.sarom_back.domain.auth.presentation.dto.response

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty

data class SignInRequest(
    @field:NotNull
    val username: String,

    @field:NotEmpty
    val password: String
)
