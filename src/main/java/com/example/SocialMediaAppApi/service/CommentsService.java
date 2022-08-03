package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Comment;
import com.example.SocialMediaAppApi.repository.CommentsRepository;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.CommentsRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final TokenService tokenService;

    public Collection<Comment> getCommentsForPostId (Long post_id){
        return commentsRepository.getCommentsForPostId(postsRepository.getById(post_id));
    }

    public Collection<Comment> getCommentsForPostIdAndUserId (Long post_id, Long user_id){
        return commentsRepository.getCommentsForPostIdAndUserId(postsRepository.getById(post_id), userRepository.getById(user_id) );
    }

    public String postComment(CommentsRequest request){

       Token newToken = tokenService.verifyToken(request.getToken());

        Comment comment = new Comment(

                request.getText(),
                newToken.getUser(),
                request.getPost()


        );

        commentsRepository.save(comment);
        return "Comment posted";

    }

//    public void deleteCommentByIdAndPostAndUser(Long post_id,Long id, Long user_id){
//        return
//    }


}
