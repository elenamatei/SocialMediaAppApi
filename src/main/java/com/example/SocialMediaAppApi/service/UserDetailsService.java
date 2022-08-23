package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.request.UserDetailsRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class UserDetailsService {

    private final TokenService tokenService;
    private final UserDetailsRepository userDetailsRepository;

    public String addDetails(UserDetailsRequest request){
        Token newToken = tokenService.verifyToken(request.getToken());

        String pictureURL = photoProcessing(request.getProfilePicture(), newToken.getUser().getEmail());

        Details details = new Details(
                request.getBirthPlace(),
                request.getLivingCity(),
                request.getOccupation(),
                request.getWorkPlace(),
                request.getStudies(),
                request.getDescription(),
                pictureURL,
                newToken.getUser()

        );

        userDetailsRepository.save(details);
        return "details added!";

    }

    public String photoProcessing(String photoString, String userEmail){

        String pictureName =encoder(userEmail+System.currentTimeMillis()) ;
        String[] strings = photoString.split(",");
        String extension;
        switch (strings[0]) {
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default:
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "C:\\Users\\Eliza\\Desktop\\Licenta\\SocialMediaAppApi\\src\\main\\resources\\Images\\"+ pictureName +"." + extension;
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