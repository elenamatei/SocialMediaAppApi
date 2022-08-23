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

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private Long userId;
    private String userName;
    private String text;


    public Comment(String text, Long postId,Long userId, String userName) {
        this.text = text;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
    }

}