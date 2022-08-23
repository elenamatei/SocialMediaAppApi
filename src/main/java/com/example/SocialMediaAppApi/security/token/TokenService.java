package com.example.SocialMediaAppApi.security.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokensRepository tokensRepository;


    public String encoder(String input)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String createToken(String userEmail){


        return this.encoder(userEmail+System.currentTimeMillis());
    }

    public Collection<Token> getAllTokensByToken(String inputToken){
        return tokensRepository.getAllTokensByToken(inputToken);
    }

    public Token verifyToken(String myToken){

        Collection<Token> tokens = getAllTokensByToken(myToken);
        if (!tokens.isEmpty()){
            return tokens.iterator().next();
        }else
            return null;

    }

}