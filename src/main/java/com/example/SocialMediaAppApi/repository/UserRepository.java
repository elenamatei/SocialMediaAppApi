package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAll();

    @Override
    User getById(Long id);

    @Query("SELECT u FROM User u WHERE u.birthDate=?1")
    List<User> getBirthdays(Date birthDate);


}