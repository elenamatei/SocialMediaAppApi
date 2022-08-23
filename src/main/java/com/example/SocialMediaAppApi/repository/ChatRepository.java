package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE (c.receiverId=?1 and c.senderId=?2) OR (c.receiverId=?2 and c.senderId=?1)")
    List<Chat> getConversation(Long id1, Long id2);

    @Query("SELECT c FROM Chat c WHERE c.receiverId=?1  OR c.senderId=?1")
    List<Chat> getConversations(Long id1);

}