package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.repository.CommentsRepository;
import com.example.SocialMediaAppApi.request.CommentsRequest;
import com.example.SocialMediaAppApi.request.PostCommentsRequest;
import com.example.SocialMediaAppApi.service.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path ="/api/comments" )
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/postComment")
    public String postComment(@RequestBody CommentsRequest request)
    {
        return commentsService.postComment(request);
    }

    @PostMapping("/getCommentsForPost")
    public String getComments(@RequestBody PostCommentsRequest request1)
    {
        return commentsService.getCommentsForPostIdAndUser(request1.getPostId());
    }

}
