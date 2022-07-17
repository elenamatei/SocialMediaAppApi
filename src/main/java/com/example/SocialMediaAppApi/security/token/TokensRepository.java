package com.example.SocialMediaAppApi.security.token;

import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface TokensRepository extends JpaRepository<Token,Long> {

    @Override
    Optional<Token> findById(Long id);


    Optional<Token> findByUserId(Long user_id);

    //    @Query( "SELECT t FROM Token t WHERE t.token IS NOT NULL ")
    @Query( "SELECT t FROM Token t WHERE t.token= ?1 ")
    Collection<Token> getAllTokensByToken(String myToken);





}
