package com.example.SocialMediaAppApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Chats")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @ManyToOne
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String text;
    private LocalDate timeStamp;


    @Override
    public String toString() {
        return "Chat{" +
                "user=" + user +
                ", id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", text='" + text + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
