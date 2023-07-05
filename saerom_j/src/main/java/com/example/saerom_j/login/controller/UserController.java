package com.example.saerom_j.login.controller;

import com.example.saerom_j.login.dto.UserDto;
import com.example.saerom_j.login.dto.UserLoginResponse;
import com.example.saerom_j.login.dto.UserSignupRequest;
import com.example.saerom_j.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest userSignupRequest) {
        UserDto userDto = userService.signup(userSignupRequest);
        return new ResponseEntity<>(new UserLoginResponse(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest userSignupRequest) {
        UserDto userDto = userService.signup(userSignupRequest);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        try {
            String token = userService.login(userLoginRequest.getEmployNumber(), memberLoginRequest. getPassword());
            return new ResponseEntity<>(new UserLoginResponse(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    }
}