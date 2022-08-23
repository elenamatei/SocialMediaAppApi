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
public class UpdatePetRequest {

    private final Long id;
    private final String token;
    private final String picture;
    private final String name;
    private final String type;
    private final String race;
    private final String color;
    private final LocalDate birthDate;
    private final String gender;
    private final String favouriteFood;
    private final String description;
    private final String isNeutered;

    @JsonCreator
    public UpdatePetRequest(@JsonProperty("petId")Long id, @JsonProperty("token") String token,@JsonProperty("picture") String picture,@JsonProperty("name") String name,@JsonProperty("type") String type,@JsonProperty("race") String race,@JsonProperty("color")String color,@JsonProperty("birthDate") LocalDate birthDate,@JsonProperty("gender") String gender,@JsonProperty("favouriteFood") String favouriteFood,@JsonProperty("description") String description,@JsonProperty("isNeutered") String isNeutered) {
        this.id = id;
        this.token = token;
        this.picture = picture;
        this.name = name;
        this.type = type;
        this.race = race;
        this.color = color;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favouriteFood = favouriteFood;
        this.description = description;
        this.isNeutered = isNeutered;
    }
}
