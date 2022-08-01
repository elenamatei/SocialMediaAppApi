package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PetService {

    private final UserRepository userRepository;
    private final PetsRepository petsRepository;

    public Collection<Pet> getPetsByUserId(Long user_id){
        return petsRepository.getPetsByUserId(userRepository.getById(user_id));
    }

    public Pet getOnePetByUserId(Long user_id, Long pet_id){
        return petsRepository.getOnePetByUserId(userRepository.getById(user_id),petsRepository.getById(pet_id));
    }

}
