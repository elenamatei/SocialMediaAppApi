package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Chat;
import com.example.SocialMediaAppApi.model.Comment;
import com.example.SocialMediaAppApi.repository.CommentsRepository;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.CommentsRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final TokenService tokenService;

//    public String getCommentsForPostIdAndUser (Long postId, String token){
//        Token newToken = tokenService.verifyToken(token);
//        JSONObject response = new JSONObject();
//        List<Comment> comments = commentsRepository.getCommentsForPostIdAndUserId(postId,newToken.getUser().getId());
//        response.put("comments", comments);
//        System.out.println(comments + "ALLCOMMENTS");
//        return response.toString();
//    }
    public String getCommentsForPostIdAndUser (Long postId){
//        Token newToken = tokenService.verifyToken(token);
        JSONObject response = new JSONObject();
        List<Comment> comments = commentsRepository.getCommentsForPostId(postId);
        response.put("comments", comments);
        System.out.println(comments + "ALLCOMMENTS");
        return response.toString();
    }

//    public Collection<Comment> getCommentsForPostIdAndUserId (Long post_id, Long user_id){
//        return commentsRepository.getCommentsForPostIdAndUserId(postsRepository.getById(post_id), userRepository.getById(user_id) );
//    }

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

//    public void deleteCommentByIdAndPostAndUser(Long post_id,Long id, Long user_id){
//        return
//    }


}
