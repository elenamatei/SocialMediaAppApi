package com.example.SocialMediaAppApi.request;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class LoginRequest {
    private final String email;
    private final String password;
}
