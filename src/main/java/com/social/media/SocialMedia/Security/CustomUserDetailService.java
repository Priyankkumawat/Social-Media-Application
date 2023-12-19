package com.social.media.SocialMedia.Security;

import com.social.media.SocialMedia.Exceptions.ResourceNotFoundException;
import com.social.media.SocialMedia.Models.User;
import com.social.media.SocialMedia.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null) throw new ResourceNotFoundException("User","username",username);
        return user;
    }
}
