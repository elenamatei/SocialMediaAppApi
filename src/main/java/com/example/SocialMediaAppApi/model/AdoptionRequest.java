package com.example.SocialMediaAppApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="AdoptionRequests")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequest {

    @ManyToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //private Long pet_id;
    private Long adminUserId;
    private Long clientUserId;
    private Boolean isAccepted = false;

    @Override
    public String toString() {
        return "AdoptionRequest{" +
                "user=" + user +
                ", pet=" + pet +
                ", id=" + id +
                ", adminUserId=" + adminUserId +
                ", clientUserId=" + clientUserId +
                ", isAccepted=" + isAccepted +
                '}';
    }
}
