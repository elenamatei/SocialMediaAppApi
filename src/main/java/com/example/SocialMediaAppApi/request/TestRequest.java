package com.example.SocialMediaAppApi.request;



import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;


@Getter
@EqualsAndHashCode
@ToString

public class TestRequest {

    private final String token;

    @JsonCreator
    public TestRequest(String token) {
        this.token = token;
    }
}
