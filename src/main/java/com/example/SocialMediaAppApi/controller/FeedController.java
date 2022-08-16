package com.example.SocialMediaAppApi.controller;


import com.example.SocialMediaAppApi.request.TestRequest;
import com.example.SocialMediaAppApi.service.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/feed")
@AllArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public String feed(@RequestBody TestRequest request){

        return feedService.feed(request.getToken());
    }

    @PostMapping("/birthdays")
    public String birthdays(@RequestBody Date birthDate){
        return feedService.getBirthdays(birthDate);
    }

}
