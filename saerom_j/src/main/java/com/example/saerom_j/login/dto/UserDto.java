package com.example.saerom_j.login.dto;

import com.example.saerom_j.login.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder

public class UserDto {
    private Long id;
    private String uId;
    private String name;
    private String pwd;
    private String email;

    public static UserDto fromEntity(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .uId(user.getUId())
                .name(user.getName())
                .pwd(user.getPwd())
                .email(user.getEmail())
                .build();
        return userDto;
    }
}
