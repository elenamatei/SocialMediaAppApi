package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PetController {

    private final PetsRepository petsRepository;
    private final PetService petService;

    @GetMapping("/myPets/{user_id}")
    String allUserPets(@PathVariable Long user_id) {
        return petService.getPetsByUserId(user_id);
    }

   @GetMapping("/pets/{type}/{user_id}")
    String petForUser(@PathVariable("type") String type , @PathVariable("user_id") Long user_id) {
       return petService.getPetsForUserByType(type, user_id);
    }


    @GetMapping("/petProfile/{user_id}/{id}")
    Pet oneUserPet(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id ) {
        System.out.println(petService.getPetByUserId(user_id,id));
        return petService.getPetByUserId(user_id,id);
//                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/editPetProfile/{user_id}/{id}")
//    @PutMapping("/editPetProfile/{id}")
    Pet editPet(@RequestBody Pet updatedPet, @PathVariable("user_id") Long user_id, @PathVariable("id") Long id) {

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
