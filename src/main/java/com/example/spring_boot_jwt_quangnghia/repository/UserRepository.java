package com.example.spring_boot_jwt_quangnghia.repository;

import com.example.spring_boot_jwt_quangnghia.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
