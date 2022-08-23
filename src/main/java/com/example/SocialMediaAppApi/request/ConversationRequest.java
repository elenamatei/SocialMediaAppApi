package com.example.SocialMediaAppApi.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ConversationRequest {

    private final Long receiverId;
    private final String token;

    @JsonCreator
    public ConversationRequest( @JsonProperty("receiverId") Long receiverId, @JsonProperty("token") String token) {
        this.receiverId = receiverId;
        this.token = token;
    }
}
