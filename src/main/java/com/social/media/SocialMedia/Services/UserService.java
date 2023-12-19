package com.social.media.SocialMedia.Services;

import com.social.media.SocialMedia.Payload.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Integer id);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUser();
    void deleteUserByUsername(String username);
}