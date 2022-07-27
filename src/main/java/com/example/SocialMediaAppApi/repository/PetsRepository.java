package com.example.SocialMediaAppApi.repository;


import com.example.SocialMediaAppApi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pet, Long> {
    @Override
    Optional<Pet> findById(Long id);

    @Override
    Pet getById(Long id);

    Pet getByIdAndUser(Long id, Long user_id);


//    List<Pet> findByUser_Id(Long user_id);
    List<Pet> findByUser_Id();
}
