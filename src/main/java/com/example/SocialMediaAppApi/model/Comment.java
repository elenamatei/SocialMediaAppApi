package com.example.SocialMediaAppApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Comments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //private Long post_id;
//    private Long userId;
    private String text;

    @Override
    public String toString() {
        return "Comment{" +
                "post=" + post +
                ", user=" + user +
                ", id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
