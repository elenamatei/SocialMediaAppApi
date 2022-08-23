package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.request.PostRequest;
import com.example.SocialMediaAppApi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/createPost")
@AllArgsConstructor
public class CreatePostController {
    private final PostService postService;

    @PostMapping
    public String publishPost(@RequestBody PostRequest request){
        return postService.publishPost(request);
    }

}
