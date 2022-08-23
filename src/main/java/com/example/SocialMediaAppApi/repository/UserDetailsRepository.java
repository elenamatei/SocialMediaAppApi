package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<Details, Long> {

    @Override
    Details getById(Long id);

    @Query("SELECT d FROM Details d")
    List<Details> findAll();


    @Query("SELECT d FROM Details d WHERE d.user.id =?1 ")
    Details getDetailsForUser(Long user_id);
}