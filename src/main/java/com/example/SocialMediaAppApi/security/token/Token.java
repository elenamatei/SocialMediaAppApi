package com.example.SocialMediaAppApi.security.token;


import com.example.SocialMediaAppApi.model.User;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Entity
@Table(name = "Tokens")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String token;

    public Token( User user, String token) {

    }

    @Override
    public String toString() {
        return "Token{" +

                "token='" + token + '\'' +
                '}';
    }
}
