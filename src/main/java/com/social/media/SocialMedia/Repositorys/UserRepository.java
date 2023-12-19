package com.social.media.SocialMedia.Repositorys;

import com.social.media.SocialMedia.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}