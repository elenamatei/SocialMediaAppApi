package com.example.SocialMediaAppApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name="Pets")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Pet {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private Long user_id;
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
    private Boolean isAdoption = false;
//    private String age;

    public Pet(String name, String type, String race, String color, LocalDate birthDate, String gender, String favouriteFood, String description, String isNeutered, String picture, User user) {
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
        this.user = user;
//        this.age = calculateAge(birthDate);

    }


    public static String calculateAge(LocalDate birthDate)
    {

        LocalDate currentDate = LocalDate.now();

        if ((birthDate != null) && (currentDate != null))
        {
            Period period = Period.between(birthDate, currentDate);
            if(period.getYears() == 0) return period.getMonths()+" months";
            else
            if(period.getMonths() == 0){
                return period.getYears() + " years";
            } else

                return period.getYears()+" years and " + period.getMonths() + " months";
        }
        else
        {
            return null;
        }
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
