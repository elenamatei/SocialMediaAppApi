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
public class PostRequest {
    private final String picture;
    private final String text;
    private final LocalDate postDate;
    private final String token;


    @JsonCreator
    public PostRequest(@JsonProperty("picture")String picture,@JsonProperty("text")String text,@JsonProperty("postDate")LocalDate postDate,@JsonProperty("token")String token){
        this.picture = picture;
        this.text = text;
        this.postDate = postDate;
        this.token = token;
    }


}
