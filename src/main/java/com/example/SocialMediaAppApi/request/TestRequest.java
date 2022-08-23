package com.example.SocialMediaAppApi.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@EqualsAndHashCode
@ToString

public class TestRequest {

    private final String token;

    @JsonCreator
    public TestRequest(@JsonProperty("token")String token) {
        this.token = token;
    }
}