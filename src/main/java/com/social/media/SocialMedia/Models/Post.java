package com.social.media.SocialMedia.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    private Date addedDate;

    private String imageName;

    @ManyToOne
    @JoinColumn
    private User user;
}