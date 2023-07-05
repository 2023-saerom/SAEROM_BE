package com.example.saerom_j.login.dto;

import com.example.saerom_j.login.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

public class UserSignupRequest {
    private String uId;
    private String name;
    private String pwd;
    private String email;
    public User toEntity(String pwd){
        return User.builder()
                .uId(this.uId)
                .name(this.name)
                .pwd(this.pwd)
                .email(this.email)
                .build();
    }
}
