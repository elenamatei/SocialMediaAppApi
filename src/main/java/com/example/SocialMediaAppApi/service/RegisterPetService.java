package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.repository.PetsRepository;
import com.example.SocialMediaAppApi.request.RegisterPetRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class RegisterPetService {

    private final TokenService tokenService;
    private final PetsRepository petsRepository;


    public String register(RegisterPetRequest request){

       Token newToken = tokenService.verifyToken(request.getToken());

       String pictureURL = photoProcessing(request.getPicture(), newToken.getUser().getEmail());

        Pet pet = new Pet(

        request.getName(),
        request.getType(),
        request.getRace(),
        request.getColor(),
        request.getBirthDate(),
        request.getGender(),
        request.getFavouriteFood(),
        request.getDescription(),
        request.getIsNeutered(),
        pictureURL,
        newToken.getUser()

        );

        petsRepository.save(pet);
        JSONObject response = new JSONObject();
        response.put("addedPet",1);
        return response.toString();
    }

    public String photoProcessing(String photoString, String userEmail){

        String pictureName =encoder(userEmail+System.currentTimeMillis()) ;
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
//        String path = "/resources/Images/"+ pictureName +"." + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
            System.out.println(path);
            path = "/api/uploads/"+ pictureName +"." + extension;
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encoder(String input)
    {
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
