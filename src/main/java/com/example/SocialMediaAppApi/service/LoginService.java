package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.LoginRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final UserRepository userRepository;


    public String login(LoginRequest request){

        JSONObject response = new JSONObject();
        JSONObject errorResponse  = new JSONObject();

        boolean emailIsValid = emailValidator.test(request.getEmail());
        if(!emailIsValid){
            errorResponse.put("errorCode", 1);
            response.put("error", errorResponse);
            return response.toString();
        }
        User user = new User(
                request.getEmail(),
                request.getPassword()
        );
        String result =  userService.loginUser(user);
        if(result == null){
            errorResponse.put("errorCode",2);
            response.put("error", errorResponse);
            return response.toString();
        }

        response.put("token", result);

        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        Long idResponse = userOptional.get().getId();

        response.put("user_id",idResponse);
        return  response.toString();

    }


}
