package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    @Override
    List<Post> findAll();

    @Override
    Post getById(Long aLong);
}
