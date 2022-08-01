package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.request.PostRequest;
import com.example.SocialMediaAppApi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class CreatePostController {
    private final PostService postService;

    @PostMapping
    public String publishPost(@RequestBody PostRequest request){
        return postService.publishPost(request);
    }
}
