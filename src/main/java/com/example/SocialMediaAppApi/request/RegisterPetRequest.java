package com.example.SocialMediaAppApi.request;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegisterPetRequest {
    private final String name;
    private final String type;
    private final String race;
    private final String color;
    private final LocalDate birthDate;
    private final String gender;
    private final String favouriteFood;
    private final String description;
    private final String isNeutered;
    private final String picture;
    private final String token;


}
