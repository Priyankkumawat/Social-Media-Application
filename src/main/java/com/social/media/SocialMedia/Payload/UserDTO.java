package com.social.media.SocialMedia.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Size(min=4,message = "User must me more than 4")
    public String username;
    @NotEmpty
    @Size(min=6,max = 8, message = "Password must be more than 6 and less than equal to 8")
    public String password;
    @Email
    public String email;
}