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
public class UpdateUserRequest {

    private final String token;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String gender;
    private final LocalDate birthDate;

    private final String description;
    private final String occupation;
    private final String workPlace;
    private final String profilePicture;
    private final String birthPlace;
    private final String livingCity;
    private final String studies;

    @JsonCreator
    public UpdateUserRequest(@JsonProperty("token")String token,@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("gender") String gender, @JsonProperty("birthDate") LocalDate birthDate,@JsonProperty("birthPlace") String birthPlace,@JsonProperty("livingCity") String livingCity,@JsonProperty("occupation") String occupation,@JsonProperty("workPlace") String workPlace,@JsonProperty("studies") String studies,@JsonProperty("description") String description,@JsonProperty("profilePicture") String profilePicture) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.livingCity = livingCity;
        this.occupation = occupation;
        this.workPlace = workPlace;
        this.studies = studies;
        this.description = description;
        this.profilePicture = profilePicture;

    }

}