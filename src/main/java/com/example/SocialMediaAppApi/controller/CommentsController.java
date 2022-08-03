package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.model.Comment;
import com.example.SocialMediaAppApi.repository.CommentsRepository;
import com.example.SocialMediaAppApi.request.CommentsRequest;
import com.example.SocialMediaAppApi.service.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path ="/api/comments" )
public class CommentsController {

    private final CommentsService commentsService;
    private final CommentsRepository commentsRepository;

    @PostMapping("/postComment")
    public String postComment(@RequestBody CommentsRequest request)
    {
        return commentsService.postComment(request);
    }

    @GetMapping("/postIdComments")
    Collection<Comment> commentsByPostId(@PathVariable Long post_id){

        return commentsService.getCommentsForPostId(post_id);
    }

    @GetMapping("/")
    Collection<Comment> commentsByPostIdAndUserId(@PathVariable Long post_id, @PathVariable Long user_id){

        return commentsService.getCommentsForPostIdAndUserId(post_id,user_id);
    }

    @DeleteMapping()
    void deleteComment(@PathVariable Long id) {
        commentsRepository.deleteById(id);
    }


}
