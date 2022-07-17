package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.request.LoginRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private TokenService tokenService;

    public String login(LoginRequest request){

        boolean emailIsValid = emailValidator.test(request.getEmail());
        if(!emailIsValid){
            throw new IllegalStateException("Email address not valid!");
        }
        User user = new User(
                request.getEmail(),
                request.getPassword()
        );
        return userService.loginUser(user);
    }


}
