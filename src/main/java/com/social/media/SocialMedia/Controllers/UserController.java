package com.social.media.SocialMedia.Controllers;

import com.social.media.SocialMedia.Payload.ApiResponse;
import com.social.media.SocialMedia.Payload.UserDTO;
import com.social.media.SocialMedia.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Post-user
    @PostMapping("/add")
    public ResponseEntity<?> creatUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    //Update-user
    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId){
        try{
            UserDTO userDTO1 = userService.updateUser(userDTO,userId);
            return new ResponseEntity<>(userDTO1,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //Get-user
    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        UserDTO userDTO = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    }

    //Get-all-user
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.FOUND);
    }

    //Delete-user
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String username){
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(new ApiResponse("User is deleted",true),HttpStatus.GONE);
    }
}