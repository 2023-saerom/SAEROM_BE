package com.example.sarom_back.global.security.principle

import com.example.sarom_back.domain.student.entity.StudentRepository
import com.prject.nextstep.global.exception.InvalidTokenException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component

class StudentDetailsService(
    private val studentRepository: StudentRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val parent = studentRepository.findByPhoneNumber(username!!) ?: throw InvalidTokenException

        return ParentDetails(parent.id)
    }
}