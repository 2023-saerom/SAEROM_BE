package com.example.sarom_back.domain.student.entity

import com.example.sarom_back.domain.parent.entity.StudentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: CrudRepository<StudentEntity, Int> {
    fun findByPhoneNumber(phoneNumber: String): StudentEntity?
}