package com.example.spring_boot_jwt_quangnghia.controller;

import com.example.spring_boot_jwt_quangnghia.Entity.User;
import com.example.spring_boot_jwt_quangnghia.service.TokenService;
import com.example.spring_boot_jwt_quangnghia.service.UserService;
import com.example.spring_boot_jwt_quangnghia.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

}
