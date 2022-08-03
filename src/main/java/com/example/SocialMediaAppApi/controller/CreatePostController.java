package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.request.PostRequest;
import com.example.SocialMediaAppApi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class CreatePostController {
    private final PostService postService;
    private final PostsRepository postsRepository;

    @PostMapping
    public String publishPost(@RequestBody PostRequest request){
        return postService.publishPost(request);
    }

    @GetMapping("/feed")
    List<Post> allPosts() { return postsRepository.findAll(); }

}
