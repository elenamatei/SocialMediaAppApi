package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Details;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<Details, Long> {

    @Override
    Details getById(Long id);

    @Override
    List<Details> findAll();

    List<Details> findByUser(Long user_Id);

}
