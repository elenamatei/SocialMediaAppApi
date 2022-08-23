package com.example.SocialMediaAppApi.repository;


import com.example.SocialMediaAppApi.model.Like;
import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Like, Long> {

    @Override
    List<Like> findAll();

    @Override
    List<Like> findAllById(Iterable<Long> id);

    @Override
    Like getById(Long id);

    @Query("SELECT l FROM Like  l WHERE l.post.id=?1 AND l.user.id=?2 ")
    Optional<Like> getLikes(Long post_id, Long user_id);


}
