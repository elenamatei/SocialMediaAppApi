package com.example.SocialMediaAppApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Chats")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String text;

    public Chat(Long senderId, Long receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

}