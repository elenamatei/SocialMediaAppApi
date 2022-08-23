package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.TestRequest;
import com.example.SocialMediaAppApi.request.UpdatePetRequest;
import com.example.SocialMediaAppApi.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PetController {

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
    String oneUserPet(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id ) {
        return petService.getPetByUserId(user_id,id);
    }

    @PostMapping("/editPetProfile")
    String editPet(@RequestBody UpdatePetRequest request){
        return petService.updatePet(request);
    }

    @GetMapping("/adoptions")
    String getAdoptions(){
        return petService.getAdoptions();
    }

    @PostMapping("/adoptPet/{petId}")
    String adoptPet(@RequestBody TestRequest request, @PathVariable("petId") Long petId){

         petService.adoptPet(request, petId);
         return "adopted";
    }

}