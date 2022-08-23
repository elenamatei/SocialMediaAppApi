package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Like;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.LikesRepository;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokensRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedService {

    private final PostsRepository postsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;

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


    public String getBirthdays(){
        JSONObject response = new JSONObject();
        List<User> allUsers = userRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        JSONArray allBirthdays = new JSONArray();
        for(User user: allUsers){
            if(user.getBirthDate().getDayOfMonth() == currentDate.getDayOfMonth() && user.getBirthDate().getMonth() == currentDate.getMonth()){
                JSONObject userDetails = new JSONObject();
                userDetails.put("user", JSONObject.wrap(user));
                userDetails.put("details",JSONObject.wrap(userDetailsRepository.getDetailsForUser(user.getId())));
                allBirthdays.put(userDetails);
            }
        }
        response.put("birthdays",allBirthdays);
        return response.toString();
    }

    public String insertLike (Long post_id, Long user_id){
        Like like = new Like();
        like.setUser(userRepository.findById(user_id).get());
        like.setPost(postsRepository.findById(post_id).get());
        likesRepository.save(like);
        return "inserted like";
    }

    public String deleteLike (Long post_id, Long user_id){
        Optional<Like> like = likesRepository.getLikes(post_id, user_id);
        likesRepository.delete(like.get());
        return "deleted like";
    }

    public String verifyLike (Long post_id, Long user_id){
        Optional<Like> like = likesRepository.getLikes(post_id, user_id);
        JSONObject response = new JSONObject();
        response.put("liked", like.isPresent());
        return response.toString();
    }






}



