package com.example.sarom_back.domain.parent.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ParentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    var username: String,

    var password: String,

    var nickname: String,

    var email: String
)