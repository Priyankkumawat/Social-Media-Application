package com.social.media.SocialMedia.Payload;

import lombok.Data;

@Data
public class JWTAuthRequest {
    private String username;
    private String password;
}