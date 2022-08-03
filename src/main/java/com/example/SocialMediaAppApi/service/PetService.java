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
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PetsRepository petsRepository;

    public Collection<Pet> getPetsByUserId(Long user_id){
        return petsRepository.getPetsByUserId(userRepository.getById(user_id));
    }

    public Optional<Pet> getPetByUserId(Long user_id, Long id){
        return petsRepository.getOnePetByUserId(userRepository.findById(user_id),petsRepository.findById(id));
    }

    public Optional<Pet> getPetById(Long id){
        return petsRepository.findById(id);
    }

    public Collection<Pet> getCats(String type){ return petsRepository.getPetsByType("Cat"); }
    public Collection<Pet> getDogs(String type){
        return petsRepository.getPetsByType("Dog");
    }
    public Collection<Pet> getRodens(String type){
        return petsRepository.getPetsByType("Roden");
    }
    public Collection<Pet> getBirds(String type){
        return petsRepository.getPetsByType("Bird");
    }
    public Collection<Pet> getFishes(String type){
        return petsRepository.getPetsByType("Fish");
    }
    public Collection<Pet> getReptiles(String type){
        return petsRepository.getPetsByType("Reptile");
    }

}
