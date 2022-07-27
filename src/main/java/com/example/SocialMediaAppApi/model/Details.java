package com.example.SocialMediaAppApi.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Details")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Details {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer petsNumber;
    private String occupation;
    private String workPlace;
    private String profilePicture;
    private String birthPlace;
    private String livingCity;
    private String studies;

    public Details( String description, Integer petsNumber, String occupation, String workPlace,String profilePicture, String birthPlace, String livingCity, String studies) {
        this.description = description;
        this.petsNumber = petsNumber;
        this.occupation = occupation;
        this.workPlace = workPlace;
        this.profilePicture = profilePicture;
        this.birthPlace = birthPlace;
        this.livingCity = livingCity;
        this.studies = studies;
    }


    public Details(String description, String occupation, String birthPlace, String workPlace, String profilePicture, String livingCity, String studies, User user) {
        this.description = description;
        this.occupation = occupation;
        this.birthPlace = birthPlace;
        this.workPlace = workPlace;
        this.profilePicture = profilePicture;
        this.livingCity = livingCity;
        this.studies = studies;
        this.user = user;

    }

//    public Details(String description, String occupation, String birthPlace, String workPlace, String profilePicture, String livingCity, String studies, String token) {
//    }
}
