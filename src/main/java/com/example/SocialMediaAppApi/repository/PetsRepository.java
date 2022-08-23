package com.example.SocialMediaAppApi.repository;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pet, Long> {
    @Override
    Optional<Pet> findById(Long id);

    @Override
    Pet getById(Long id);

    @Query("SELECT p FROM Pet p WHERE p.user.id=?1")
    Collection<Pet> getPetsByUserId(Long user_id);

    @Query("SELECT p FROM Pet p WHERE p.user.id=?1 AND p.id=?2")
    Optional<Pet> getOnePetByUserId(Long user_id, Long id);

    @Query("SELECT p FROM Pet p WHERE p.type=?1 AND p.user.id=?2")
    Collection<Pet> getPetsByTypeAndUser(String type, Long user_id);

    @Query("SELECT p FROM Pet p WHERE p.isAdoption=true")
    Collection<Pet> getAdoptions();

}