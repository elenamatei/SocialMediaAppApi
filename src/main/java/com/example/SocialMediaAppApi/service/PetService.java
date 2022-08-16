package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.UpdatePetRequest;
import com.example.SocialMediaAppApi.request.UpdateUserRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {

    private final UserRepository userRepository;
    private final PetsRepository petsRepository;
    private final TokenService tokenService;

    public String getPetsByUserId(Long user_id){
        JSONObject response = new JSONObject();
        Collection<Pet> pets = petsRepository.getPetsByUserId(user_id);
        response.put("pets", pets);
        System.out.println(pets+ "all Pets");
        return response.toString();
    }

    public Pet getPetByUserId(Long user_id, Long id){
        return petsRepository.getOnePetByUserId(user_id,id);
    }

    public Optional<Pet> getPetById(Long id){
        return petsRepository.findById(id);
    }

    public String getPetsForUserByType(String type, Long user_id){
        JSONObject response = new JSONObject();
        Collection<Pet> pets = petsRepository.getPetsByTypeAndUser(type, user_id);
        response.put("pets", pets);
        return response.toString();

    }

    public String updatePet(UpdatePetRequest request){
        JSONObject response = new JSONObject();
        Token newToken = tokenService.verifyToken(request.getToken());
        Pet pet = new Pet();
        pet.setPicture(request.getPicture());
        pet.setName(request.getName());
        pet.setType(request.getType());
        pet.setRace(request.getRace());
        pet.setColor(request.getColor());
        pet.setBirthDate(request.getBirthDate());
        pet.setGender(request.getGender());
        pet.setFavouriteFood(request.getFavouriteFood());
        pet.setDescription(request.getDescription());
        pet.setIsNeutered(request.getIsNeutered());

        petsRepository.save(pet);

        response.put("updateResults", 1);
        return response.toString();
    }


}
