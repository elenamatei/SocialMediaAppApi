package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.UpdateUserRequest;
import com.example.SocialMediaAppApi.security.PasswordEncoder;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TokensRepository tokensRepository;
    private final PetsRepository petsRepository;
    private final TokenService tokenService;
    private final PetService petService;

    public String registerUser(User user) {
        boolean emailTaken = userRepository.findByEmail(user.getEmail()).isPresent();
        if(emailTaken){
            return null;
        }

        String encodedPassword = passwordEncoder.encodePassword(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        Token newToken = new Token();

        String userToken = tokenService.createToken(user.getEmail());
        newToken.setToken(userToken);
        newToken.setUser(user);

        tokensRepository.save(newToken);
        return userToken;
    }

    public String loginUser(User user){

        String encodedPassword = passwordEncoder.encodePassword(user.getPassword());
        Optional<User> existingUser = userRepository
                .findByEmailAndPassword(user.getEmail(),encodedPassword);
        if (existingUser.isEmpty()){
            return null;
        }
        else{
        String userToken = tokenService.createToken(user.getEmail());
        Token newToken = new Token();
        newToken.setToken(userToken);
        newToken.setUser(existingUser.get());

        tokensRepository.save(newToken);
        return userToken;}
    }

    public String getUserById(Long id){
        JSONObject response = new JSONObject();
        User user = userRepository.findById(id).get();
        Details details = userDetailsRepository.getDetailsForUser(id);
        response.put("user",JSONObject.wrap(user));
        response.put("details",JSONObject.wrap(details));

        Collection<Pet> userPets = petsRepository.getPetsByUserId(id);
        int numberOfPets = userPets.size();
        response.put("pets", JSONObject.wrap(numberOfPets));
        return response.toString();
    }

    public String getAllUsers(){
        JSONObject response = new JSONObject();
        List<Details> allDetails = userDetailsRepository.findAll();
        response.put("details",allDetails);
        return response.toString();
    }

    public String updateUser(UpdateUserRequest request){
        JSONObject response = new JSONObject();
        Token newToken = tokenService.verifyToken(request.getToken());
        User user = newToken.getUser();
        Details details = userDetailsRepository.getDetailsForUser(user.getId());
        user.setBirthDate(request.getBirthDate());
        user.setGender(request.getGender());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());

        details.setBirthPlace(request.getBirthPlace());
        details.setLivingCity(request.getLivingCity());
        details.setOccupation(request.getOccupation());
        details.setWorkPlace(request.getWorkPlace());
        details.setStudies(request.getStudies());
        details.setDescription(request.getDescription());
        details.setProfilePicture(request.getProfilePicture());

        userRepository.save(user);
        userDetailsRepository.save(details);

        response.put("updateResults", 1);
        return response.toString();
    }


}