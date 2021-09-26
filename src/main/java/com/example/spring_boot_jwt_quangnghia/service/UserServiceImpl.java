package com.example.spring_boot_jwt_quangnghia.service;


import com.example.spring_boot_jwt_quangnghia.Entity.User;
import com.example.spring_boot_jwt_quangnghia.authen.UserPrincipal;
import com.example.spring_boot_jwt_quangnghia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserPrincipal findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();

        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles())

                user.getRoles().forEach(r -> {
                    authorities.add(r.getRoleKey());
                    r.getPermissions().forEach(
                            p -> authorities.add(p.getPermissionKey()));
                });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUserName(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
