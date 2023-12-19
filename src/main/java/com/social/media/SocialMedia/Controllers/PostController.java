package com.social.media.SocialMedia.Controllers;

import com.social.media.SocialMedia.Payload.PostDTO;
import com.social.media.SocialMedia.Services.PostService;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId){
        try{
            PostDTO postDTO1 = postService.createPost(postDTO, userId);
            return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDTO postDTO,@PathVariable Integer postId){
        PostDTO postDTO1 = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Integer postId){
        PostDTO postDTO = postService.getPost(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.FOUND);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPost(Integer pageNumber, Integer pageSize){
        List<PostDTO> postDTOList = postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }
    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<?> getAllPostByUser(@PathVariable Integer userId){
        List<PostDTO> postDTOList = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDTOList,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
