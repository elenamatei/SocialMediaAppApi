package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.request.RegisterPetRequest;
import com.example.SocialMediaAppApi.service.RegisterPetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registerPet")
@AllArgsConstructor
public class RegisterPetController {

    private RegisterPetService registerPetService;

    @PostMapping
    public String petRegister(@RequestBody RegisterPetRequest request){
        return registerPetService.register(request);
    }
}
