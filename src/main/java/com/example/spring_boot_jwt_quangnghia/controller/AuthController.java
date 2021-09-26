package com.example.spring_boot_jwt_quangnghia.controller;

import com.example.spring_boot_jwt_quangnghia.Entity.Token;
import com.example.spring_boot_jwt_quangnghia.Entity.User;
import com.example.spring_boot_jwt_quangnghia.authen.UserPrincipal;
import com.example.spring_boot_jwt_quangnghia.service.TokenService;
import com.example.spring_boot_jwt_quangnghia.service.UserService;
import com.example.spring_boot_jwt_quangnghia.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(),userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account or password  is not vaild");

        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }
}
