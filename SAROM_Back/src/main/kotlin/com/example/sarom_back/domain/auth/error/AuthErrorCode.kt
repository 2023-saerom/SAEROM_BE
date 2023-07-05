package com.example.sarom_back.domain.auth.error

import com.prject.nextstep.global.error.ErrorProperty

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    INVALID_PASSWORD_EXCEPTION(400, "Invalid Password"),
    USERNAME_NOT_FOUND(404, "User Name Not Found"),
    EMAIL_ALREADY_EXISTS(409, "Email Already Exists"),
    USERNAME_ALREADY_EXISTS(409, "Username Already Exists")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}