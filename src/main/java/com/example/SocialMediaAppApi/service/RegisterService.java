package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegisterService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final UserRepository userRepository;

    public String register(RegisterRequest request) {

        JSONObject response = new JSONObject();
        JSONObject errorResponse  = new JSONObject();

        boolean emailIsValid = emailValidator.test(request.getEmail());
        if(!emailIsValid){
            errorResponse.put("errorCode", 1);
            response.put("error", errorResponse);
            return response.toString();
        }

        LocalDate dateT = LocalDate.now();

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getGender(),
                request.getBirthDate(),
                dateT

        );

        String result = userService.registerUser(user);
        response.put("token", result);
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        Long idResponse = userOptional.get().getId();
        response.put("user_id",idResponse);

        String userName = userOptional.get().getFirstName() +" "+ userOptional.get().getLastName();
        response.put("full_name",userName);

        return  response.toString();

    }
}