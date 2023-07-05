package com.example.sarom_back.domain.parent.entity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParentRepository: CrudRepository<ParentEntity, Int> {
    fun findByUsername(username: String): ParentEntity?
}