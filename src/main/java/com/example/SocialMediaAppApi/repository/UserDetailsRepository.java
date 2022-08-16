package com.example.SocialMediaAppApi.repository;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.Pet;
import com.example.SocialMediaAppApi.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Details, Long> {

    @Override
    Details getById(Long id);

//    @Override
//    List<Details> findAll();
    @Query("SELECT d FROM Details d")
    List<Details> findAll();



    List<Details> findByUser(Long user_Id);

//    @Query("SELECT d FROM Details d WHERE d.user.id=?1 ")
//    Details getDetailsForUser(User user);

    @Query("SELECT d FROM Details d WHERE d.user.id =?1 ")
    Details getDetailsForUser(Long user_id);

}
