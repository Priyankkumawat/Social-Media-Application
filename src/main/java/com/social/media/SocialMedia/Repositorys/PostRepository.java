package com.social.media.SocialMedia.Repositorys;

import com.social.media.SocialMedia.Models.Post;
import com.social.media.SocialMedia.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
}