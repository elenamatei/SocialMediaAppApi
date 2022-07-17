package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User getById(Long id);
    Optional<User> findByEmailAndPassword(String email, String password);
}