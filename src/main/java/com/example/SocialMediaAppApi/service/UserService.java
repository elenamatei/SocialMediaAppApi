package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Pet;
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
public class UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TokensRepository tokensRepository;
    private final PetsRepository petsRepository;
    private final TokenService tokenService;

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

        if(request.getProfilePicture() != "")
            details.setProfilePicture(photoProcessing(request.getProfilePicture(), newToken.getUser().getEmail()));

        userRepository.save(user);
        userDetailsRepository.save(details);

        response.put("updateResults", 1);
        return response.toString();
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