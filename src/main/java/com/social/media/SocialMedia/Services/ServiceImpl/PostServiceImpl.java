package com.social.media.SocialMedia.Services.ServiceImpl;

import com.social.media.SocialMedia.Exceptions.ResourceNotFoundException;
import com.social.media.SocialMedia.Models.Post;
import com.social.media.SocialMedia.Models.User;
import com.social.media.SocialMedia.Payload.PostDTO;
import com.social.media.SocialMedia.Repositorys.PostRepository;
import com.social.media.SocialMedia.Repositorys.UserRepository;
import com.social.media.SocialMedia.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId.toString()));
        Post post = postFromDTO(postDTO);
        post.setAddedDate(new Date());
        post.setImageName("default.png");
        post.setUser(user);
        post = postRepository.save(post);
        user.getPostList().add(post);
        userRepository.save(user);
        return postDTOFromPost(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        if(postDTO.content != null) post.setContent(postDTO.content);
        if(postDTO.imageName != null) post.setImageName(postDTO.imageName);
        Post savedPost = postRepository.save(post);
        return postDTOFromPost(savedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        postRepository.delete(post);
    }

    @Override
    public PostDTO getPost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId.toString()));
        return postDTOFromPost(post);
    }

    @Override
    public List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize){
        //Pageable p = (Pageable) PageRequest.of(pageNumber,pageSize);
        //Page<Post> postPage = postRepository.findAll(p);
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map(this::postDTOFromPost).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getAllPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId.toString()));
        List<Post> posts = user.getPostList();
        return posts.stream().map(this::postDTOFromPost).collect(Collectors.toList());
    }

    private Post postFromDTO(PostDTO postDTO){
        return modelMapper.map(postDTO, Post.class);
    }
    private PostDTO postDTOFromPost(Post post){
        return modelMapper.map(post,PostDTO.class);
    }
}