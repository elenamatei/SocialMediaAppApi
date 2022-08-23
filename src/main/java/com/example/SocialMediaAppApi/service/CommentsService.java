package com.example.SocialMediaAppApi.service;

import com.example.SocialMediaAppApi.model.Comment;
import com.example.SocialMediaAppApi.repository.CommentsRepository;
import com.example.SocialMediaAppApi.request.CommentsRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final TokenService tokenService;

    public String getCommentsForPostIdAndUser (Long postId){
        JSONObject response = new JSONObject();
        List<Comment> comments = commentsRepository.getCommentsForPostId(postId);
        response.put("comments", comments);
        return response.toString();
    }

    public String postComment(CommentsRequest request){

       Token newToken = tokenService.verifyToken(request.getToken());

        Comment comment = new Comment(

                request.getText(),
                request.getPostId(),
                newToken.getUser().getId(),
                request.getUserName()

        );

        commentsRepository.save(comment);
        JSONObject response = new JSONObject();
        response.put("commentPosted",1);
        return response.toString();

    }

}