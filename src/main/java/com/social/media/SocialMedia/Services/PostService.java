package com.social.media.SocialMedia.Services;

import com.social.media.SocialMedia.Models.Post;
import com.social.media.SocialMedia.Models.User;
import com.social.media.SocialMedia.Payload.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Integer userId);
    PostDTO updatePost(PostDTO postDTO, Integer postId);
    void deletePost(Integer postId);
    PostDTO getPost(Integer postId);
    List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);
    List<PostDTO> getAllPostByUser(Integer userId);
}