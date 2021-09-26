package com.example.spring_boot_jwt_quangnghia.service;


import com.example.spring_boot_jwt_quangnghia.Entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}
