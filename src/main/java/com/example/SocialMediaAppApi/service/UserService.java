package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.security.PasswordEncoder;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokensRepository tokensRepository;
    private final TokenService tokenService;
    private final static String USER_NOT_FOUND = "User with email %s wasn't found";


    public String registerUser(User user) {
        boolean emailTaken = userRepository.findByEmail(user.getEmail()).isPresent();
        if(emailTaken){
//            throw new IllegalStateException("User with this email already exists!");
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
//                .isPresent();
        if (existingUser.isEmpty()){
          throw new IllegalStateException("User does not exists! Please register first!");

        }
        else{
        String userToken = tokenService.createToken(user.getEmail());
        Token newToken = new Token();
        newToken.setToken(userToken);
//            System.out.println(existingUser.toString());
        newToken.setUser(existingUser.get());

        tokensRepository.save(newToken);
        return userToken;}

    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }


}

