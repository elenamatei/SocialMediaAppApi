package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.TestRequest;
import com.example.SocialMediaAppApi.request.UpdatePetRequest;
import com.example.SocialMediaAppApi.request.UpdateUserRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {

    private final PetsRepository petsRepository;
    private final TokenService tokenService;

    public String getPetsByUserId(Long user_id){
        JSONObject response = new JSONObject();

        Collection<Pet> pets = petsRepository.getPetsByUserId(user_id);

        response.put("pets", pets);
        return response.toString();
    }

    public String getAdoptions(){
        JSONObject response = new JSONObject();

        Collection<Pet> adoptions = petsRepository.getAdoptions();

        response.put("adoptions", adoptions);
        return response.toString();
    }

    public String getPetByUserId(Long user_id, Long id){
        JSONObject response = new JSONObject();
        response.put("ownerId", user_id);

        Pet pet = petsRepository.getOnePetByUserId(user_id,id).get();

        response.put("petProfile",JSONObject.wrap(pet));
        return response.toString();
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

        Pet pet = this.getPetById(request.getId()).get();
        if(request.getPicture() != "")
            pet.setPicture(photoProcessing(request.getPicture(), newToken.getUser().getEmail()));

        pet.setName(request.getName());
        pet.setType(request.getType());
        pet.setRace(request.getRace());
        pet.setColor(request.getColor());
        pet.setBirthDate(request.getBirthDate());
        pet.setGender(request.getGender());
        pet.setFavouriteFood(request.getFavouriteFood());
        pet.setDescription(request.getDescription());
        pet.setIsNeutered(request.getIsNeutered());
        pet.setUser(newToken.getUser());

        petsRepository.save(pet);

        response.put("updateResults", 1);
        return response.toString();
    }

    public void adoptPet(TestRequest request, Long petId){
        Token newToken = tokenService.verifyToken(request.getToken());
        Pet pet = this.getPetById(petId).get();

        pet.setUser(newToken.getUser());
        pet.setIsAdoption(false);
        petsRepository.save(pet);

    }

    public String photoProcessing(String photoString, String userEmail) {
        String pictureName =encoder(userEmail + System.currentTimeMillis());
        String[] strings = photoString.split(",");
        String extension;

        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "C:\\Users\\Eliza\\Desktop\\Licenta\\SocialMediaAppApi\\src\\main\\resources\\Images\\"+ pictureName +"." + extension;

        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
            path = "/api/uploads/"+ pictureName +"." + extension;
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encoder(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}