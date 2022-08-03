package com.example.SocialMediaAppApi.repository;


import com.example.SocialMediaAppApi.model.Comment;
import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    @Override
    Comment getById(Long id);

    @Query("SELECT c FROM Comment c WHERE c.post.id=?1")
    Collection<Comment> getCommentsForPostId(Post post);

    @Query("SELECT c FROM Comment c WHERE c.post.id=?1 AND c.user.id=?2")
    Collection<Comment> getCommentsForPostIdAndUserId(Post post, User user);

    @Override
    void deleteById(Long id);

    //    @Query("SELECT c FROM Comment c WHERE c.post.id=?1 AND c.id=?2 AND c.user.id=?3")
//    void deleteByIdAndUserAndPost(Long post_id, Long id, Long user_id);
}
