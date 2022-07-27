package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PetController {

    private final PetsRepository petsRepository;

//    @GetMapping("/myPets/{user_id}")
    @GetMapping("/myPets}")
    List<Pet> allUserPets() {
        return petsRepository.findByUser_Id();
    }


//    @GetMapping("/petProfile/{user_id}/{id}")
    @GetMapping("/petProfile/{id}")
    Pet oneUserPet(@PathVariable Long id) {

        return petsRepository.getById(id);
//                .orElseThrow(() -> new UserNotFoundException(id));
    }

//    @PutMapping("/editPetProfile/{user_id}/{id}")
    @PutMapping("/editPetProfile/{id}")
    Pet editPet(@RequestBody Pet updatedPet, @PathVariable Long id) {

        return petsRepository.findById(id)
                .map(pet -> {
                    pet.setPicture(updatedPet.getPicture());
                    pet.setName(updatedPet.getName());
                    pet.setType(updatedPet.getType());
                    pet.setRace(updatedPet.getRace());
                    pet.setColor(updatedPet.getColor());
                    pet.setBirthDate(updatedPet.getBirthDate());
                    pet.setGender(updatedPet.getGender());
                    pet.setFavouriteFood(updatedPet.getFavouriteFood());
                    pet.setDescription(updatedPet.getDescription());
                    //age?


                    return petsRepository.save(pet);
                })
                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
                    return null;
                });
    }


}
