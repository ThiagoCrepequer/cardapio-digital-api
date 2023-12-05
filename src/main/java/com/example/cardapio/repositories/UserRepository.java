package com.example.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cardapio.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
