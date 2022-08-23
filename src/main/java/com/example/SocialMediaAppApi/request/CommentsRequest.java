package com.example.SocialMediaAppApi.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString

public class CommentsRequest {

    private final String text;
    private final String token;
    private final Long postId;
    private final String userName;


    @JsonCreator
    public CommentsRequest(@JsonProperty("text")String text,@JsonProperty("postId")Long postId, @JsonProperty("token") String token, @JsonProperty("userName") String userName){

        this.text = text;
        this.postId = postId;
        this.token = token;
        this.userName = userName;

    }

}