package com.example.SocialMediaAppApi.request;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString

public class RegisterRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final LocalDate birthDate;
    private final LocalDate joinedDate;
    private final String gender;

    @JsonCreator
    public RegisterRequest(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("gender") String gender, @JsonProperty("birthDate") LocalDate birthDate, @JsonProperty("joinedDate") LocalDate joinedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.joinedDate = joinedDate;
    }
}