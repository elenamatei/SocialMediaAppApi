package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.TestRequest;
import com.example.SocialMediaAppApi.service.ChatService;
import com.example.SocialMediaAppApi.service.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/feed")
@AllArgsConstructor
public class FeedController {
    private final FeedService feedService;
    private final ChatService chatService;

    @PostMapping
    public String feed(@RequestBody TestRequest request){

        return feedService.feed(request.getToken());
    }

    @PostMapping("/birthdays")
    public String birthdays(){
        return feedService.getBirthdays();
    }

    @PostMapping("/conversations")
    public String conversations(@RequestBody TestRequest request){
        return chatService.getConversations(request.getToken());
    }

    @GetMapping("/giveLike/{post_id}/{user_id}")
    public String giveLike(@PathVariable Long post_id, @PathVariable Long user_id){
        return feedService.insertLike(post_id, user_id);
    }

    @GetMapping("/takeLike/{post_id}/{user_id}")
    public String takeLike(@PathVariable Long post_id, @PathVariable Long user_id){
        return feedService.deleteLike(post_id, user_id);
    }

    @GetMapping("/verifyLike/{post_id}/{user_id}")
    public String verifyLike(@PathVariable Long post_id, @PathVariable Long user_id){
        return feedService.verifyLike(post_id, user_id);
    }

}
