package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    @Override
    List<Post> findAll();

    @Query("SELECT p FROM Post p  ORDER BY p.id DESC")
    List<Post> getAllPosts();

    @Override
    Post getById(Long id);

    @Override
    void deleteById(Long id);
}
