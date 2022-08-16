package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedService {

    private final PostsRepository postsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;

    public String feed(String token){

        JSONObject response = new JSONObject();
        List<Post> allPosts = postsRepository.getAllPosts();
        response.put("posts", allPosts);

        boolean hasDetails = false;
        Token newToken = tokensRepository.getToken(token);
        User user = newToken.getUser();
        Long userId = user.getId();
        Details details = userDetailsRepository.getDetailsForUser(userId);
        if (details != null){
            hasDetails = true;
        }
        response.put("hasDetails", hasDetails);


        return response.toString();
    }


    public String getBirthdays(Date birthDate){
        JSONObject response = new JSONObject();
        List<User> allBirthdays = userRepository.getBirthdays(birthDate);
        response.put("birthdays",allBirthdays);
        System.out.println(response);
        return response.toString();
    }
}



