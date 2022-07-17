package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(RegisterRequest request) {
        boolean emailIsValid = emailValidator.test(request.getEmail());
        if(!emailIsValid){
            throw new IllegalStateException("Email address not valid!");
        }

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getGender(),
                request.getBirthDate(),
                request.getJoinedDate()

        );


        return userService.registerUser(user);
    }
}
