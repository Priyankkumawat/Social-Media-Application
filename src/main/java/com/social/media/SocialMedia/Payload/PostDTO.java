package com.social.media.SocialMedia.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PostDTO {
    @NotEmpty
    public String content;
    public Date addedDate;
    public String imageName;
}