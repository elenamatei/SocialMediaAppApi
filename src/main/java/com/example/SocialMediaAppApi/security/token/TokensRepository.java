package com.example.SocialMediaAppApi.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TokensRepository extends JpaRepository<Token,Long> {

    @Override
    Optional<Token> findById(Long id);

    @Query( "SELECT t FROM Token t WHERE t.token= ?1 ")
    Token getToken(String myToken);

    @Query( "SELECT t FROM Token t WHERE t.token= ?1 ")
    Collection<Token> getAllTokensByToken(String myToken);

}