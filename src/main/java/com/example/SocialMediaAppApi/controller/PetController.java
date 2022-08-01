package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
public class PetController {

    private final PetsRepository petsRepository;
    private final PetService petService;

    @GetMapping("/myPets/{user_id}")
    Collection<Pet> allUserPets(@PathVariable Long user_id) {
        return petService.getPetsByUserId(user_id);
    }


    @GetMapping("/petProfile/{user_id}/{id}")
    Pet oneUserPet(@PathVariable Long user_id,@PathVariable Long id ) {

        return petService.getOnePetByUserId(user_id,id);
//                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/editPetProfile/{user_id}/{id}")
//    @PutMapping("/editPetProfile/{id}")
    Pet editPet(@RequestBody Pet updatedPet, @PathVariable Long user_id, @PathVariable Long id) {

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
