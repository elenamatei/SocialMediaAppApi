package com.example.SocialMediaAppApi.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString

public class PostCommentsRequest {

    private final String token;
    private final Long postId;

    @JsonCreator
    public PostCommentsRequest(@JsonProperty("token") String token,@JsonProperty("postId") Long postId) {
        this.token = token;
        this.postId = postId;
    }
}
