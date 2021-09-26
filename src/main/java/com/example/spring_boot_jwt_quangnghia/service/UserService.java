package com.example.spring_boot_jwt_quangnghia.service;


import com.example.spring_boot_jwt_quangnghia.Entity.User;
import com.example.spring_boot_jwt_quangnghia.authen.UserPrincipal;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}
