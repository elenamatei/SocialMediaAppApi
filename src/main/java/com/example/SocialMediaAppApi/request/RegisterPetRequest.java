package com.example.SocialMediaAppApi.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString

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
    private final Boolean isAdoption;
    private final String token;

    @JsonCreator
    public RegisterPetRequest(@JsonProperty("name")String name,@JsonProperty("type") String type,@JsonProperty("race") String race,@JsonProperty("color") String color,@JsonProperty("birthDate") LocalDate birthDate,@JsonProperty("gender") String gender,@JsonProperty("favouriteFood") String favouriteFood,@JsonProperty("description") String description,@JsonProperty("isNeutered") String isNeutered,@JsonProperty("picture") String picture,@JsonProperty("isAdoption") Boolean isAdoption , @JsonProperty("token") String token) {
        this.name = name;
        this.type = type;
        this.race = race;
        this.color = color;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favouriteFood = favouriteFood;
        this.description = description;
        this.isNeutered = isNeutered;
        this.picture = picture;
        this.isAdoption = isAdoption;
        this.token = token;

    }

}