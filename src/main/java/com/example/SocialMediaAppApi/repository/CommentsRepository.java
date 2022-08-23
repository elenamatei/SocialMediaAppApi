package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

    @Override
    Comment getById(Long id);

    @Query("SELECT c FROM Comment c WHERE c.postId=?1")
    List<Comment> getCommentsForPostId(Long post_id);

}