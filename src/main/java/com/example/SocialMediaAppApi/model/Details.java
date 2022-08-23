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


    public Details( String birthPlace,String livingCity,String occupation, String workPlace, String studies,String description,  String profilePicture,  User user) {
        this.birthPlace = birthPlace;
        this.livingCity = livingCity;
        this.occupation = occupation;
        this.workPlace = workPlace;
        this.studies = studies;
        this.description = description;
        this.profilePicture = profilePicture;
        this.user = user;

    }

}