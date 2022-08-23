package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Chat;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.ChatRepository;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.repository.UserRepository;
import com.example.SocialMediaAppApi.request.ChatRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final TokenService tokenService;

    public String sendM(ChatRequest request){

        Token newToken = tokenService.verifyToken(request.getToken());

        Chat chat = new Chat(
                newToken.getUser().getId(),
                request.getReceiverId(),
                request.getMessage()
        );
        chatRepository.save(chat);
        JSONObject response = new JSONObject();
        response.put("messageSent",1);
        return response.toString();

    }

    public String getConversation(String token, Long receiverId){
        Token newToken = tokenService.verifyToken(token);
        JSONObject response = new JSONObject();
        List<Chat> conversation = chatRepository.getConversation(newToken.getUser().getId(), receiverId);
        response.put("conversation", conversation);
        return response.toString();
    }

    public String getConversations(String token){
        Token newToken = tokenService.verifyToken(token);
        JSONObject response = new JSONObject();
        List<Chat> conversations = chatRepository.getConversations(newToken.getUser().getId());
        Set<Long> chatUsersIds = new HashSet<>();
        for(Chat chat: conversations){
            if(!chat.getReceiverId().equals(newToken.getUser().getId()))
                chatUsersIds.add(chat.getReceiverId());

            if (!chat.getSenderId().equals(newToken.getUser().getId()))
                chatUsersIds.add(chat.getSenderId());
        }
        JSONArray userConversations = new JSONArray();
        for(Long id: chatUsersIds){
            JSONObject userDetails = new JSONObject();
            User user = userRepository.findById(id).get();
            userDetails.put("user", JSONObject.wrap(user));
            userDetails.put("details",JSONObject.wrap(userDetailsRepository.getDetailsForUser(user.getId())));
            userConversations.put(userDetails);
        }
        response.put("conversations", userConversations);
        return response.toString();
    }


}
