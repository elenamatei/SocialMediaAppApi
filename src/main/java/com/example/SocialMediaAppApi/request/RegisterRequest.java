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
public class RegisterRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final LocalDate birthDate;
    private final LocalDate joinedDate;
    private final String gender;
}