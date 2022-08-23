package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.request.ChatRequest;
import com.example.SocialMediaAppApi.request.ConversationRequest;
import com.example.SocialMediaAppApi.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@AllArgsConstructor

public class ChatController {

    private final ChatService chatService;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody ChatRequest request){

        return chatService.sendM(request);
    }
    @PostMapping("/getConversation")
    public String conversation(@RequestBody ConversationRequest request){

        return chatService.getConversation(request.getToken(),request.getReceiverId());

    }

}
