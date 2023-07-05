package com.example.sarom_back.domain.auth.entity

import com.example.sarom_back.domain.auth.type.Authority
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash("tbl_refresh_token")
data class RefreshTokenEntity(

    @Id
    val token: String,

    @field:NotNull
    val userId: Int,

    @field:NotNull
    val authority: Authority,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int

)