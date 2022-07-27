package com.example.SocialMediaAppApi.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString

public class UserDetailsRequest {
    private final String description;
    private final String occupation;
    private final String workPlace;
    private final String profilePicture;
    private final String birthPlace;
    private final String livingCity;
    private final String studies;
    private final String token;



    @JsonCreator
    public UserDetailsRequest(@JsonProperty("birthPlace") String birthPlace,@JsonProperty("livingCity") String livingCity,@JsonProperty("occupation") String occupation,@JsonProperty("workPlace") String workPlace,@JsonProperty("studies") String studies,@JsonProperty("description") String description,@JsonProperty("profilePicture") String profilePicture,@JsonProperty("token") String token) {
        this.birthPlace = birthPlace;
        this.livingCity = livingCity;
        this.occupation = occupation;
        this.workPlace = workPlace;
        this.studies = studies;
        this.description = description;
        this.profilePicture = profilePicture;
        this.token = token;
    }
}
