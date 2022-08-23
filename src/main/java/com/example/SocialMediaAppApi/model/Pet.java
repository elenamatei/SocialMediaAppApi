package com.example.SocialMediaAppApi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Pets")
@Getter
@Setter
@NoArgsConstructor

public class Pet {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    private String name;
    private String type;
    private String race;
    private String color;
    private LocalDate birthDate;
    private String gender;
    private String favouriteFood;
    private String description;
    private String isNeutered;
    private Boolean isAdoption;


    public Pet(String name, String type, String race, String color, LocalDate birthDate, String gender, String favouriteFood, String description, String isNeutered, String picture, Boolean isAdoption, User user) {
        this.name = name;
        this.type = type;
        this.race = race;
        this.color = color;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favouriteFood = favouriteFood;
        this.description = description;
        this.isNeutered = isNeutered;
        this.picture = picture;
        this.isAdoption = isAdoption;
        this.user = user;

    }


    @Override
    public String toString() {
        return "Pet{" +
                "user=" + user +
                ", id=" + id +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", race='" + race + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", favouriteFood='" + favouriteFood + '\'' +
                ", description='" + description + '\'' +
                ", isAdoption=" + isAdoption +
                '}';
    }
}