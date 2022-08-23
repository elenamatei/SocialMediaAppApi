package com.example.SocialMediaAppApi.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ChatRequest {

    private final Long receiverId;
    private final String message;
    private final String token;

    @JsonCreator
    public ChatRequest(@JsonProperty("receiverId") Long receiverId,@JsonProperty("message") String message, @JsonProperty("token") String token) {

        this.receiverId = receiverId;
        this.message = message;
        this.token = token;
    }
}
