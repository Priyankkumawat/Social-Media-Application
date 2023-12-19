package com.social.media.SocialMedia.Services.ServiceImpl;

import com.social.media.SocialMedia.Exceptions.ResourceNotFoundException;
import com.social.media.SocialMedia.Models.User;
import com.social.media.SocialMedia.Payload.UserDTO;
import com.social.media.SocialMedia.Repositorys.UserRepository;
import com.social.media.SocialMedia.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return userDTOFromUser(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",userDTO.username));
        if(userDTO.username != null) user.setUserName(userDTO.username);
        if(userDTO.password != null) user.setPassword(userDTO.password);
        if(userDTO.email != null) user.setEmail(userDTO.email);
        User savedUser = userRepository.save(user);
        return userDTOFromUser(savedUser);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new ResourceNotFoundException("User","Id",username);
        }
        return userDTOFromUser(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTO=users.stream().map(this::userDTOFromUser).collect(Collectors.toList());
        return userDTO;
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new ResourceNotFoundException("User","username",username);
        }
        userRepository.delete(user);
    }

    private User userFromDTO(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

    public UserDTO userDTOFromUser(User user){
         return modelMapper.map(user,UserDTO.class);
    }
}