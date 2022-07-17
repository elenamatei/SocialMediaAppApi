package com.example.SocialMediaAppApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Posts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //private Long user_id;
    private String picture;
    private String text;
    private LocalDate postDate;


    @Override
    public String toString() {
        return "Post{" +
                "user=" + user +
                ", id=" + id +
                ", picture='" + picture + '\'' +
                ", text='" + text + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
