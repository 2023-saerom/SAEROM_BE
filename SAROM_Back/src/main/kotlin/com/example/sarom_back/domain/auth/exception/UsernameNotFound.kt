package com.example.sarom_back.domain.auth.exception

import com.example.sarom_back.domain.auth.error.AuthErrorCode
import com.prject.nextstep.global.exception.CustomException

object UsernameNotFound : CustomException (
    AuthErrorCode.USERNAME_NOT_FOUND
)