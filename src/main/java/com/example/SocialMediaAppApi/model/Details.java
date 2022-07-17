package com.example.SocialMediaAppApi.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Details")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Details {

    @OneToOne
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer petsNumber;
    private String occupation;
    private String workPlace;
    private String profilePicture;

    public Details( String description, Integer petsNumber, String occupation, String workPlace,String profilePicture) {
        this.description = description;
        this.petsNumber = petsNumber;
        this.occupation = occupation;
        this.workPlace = workPlace;
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Details{" +
                "user=" + user +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", petsNumber=" + petsNumber +
                ", occupation='" + occupation + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
