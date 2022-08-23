package com.example.SocialMediaAppApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Likes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Like{" +
                "post=" + post +
                ", id=" + id +
                ", user=" + user +
                '}';
    }
}
