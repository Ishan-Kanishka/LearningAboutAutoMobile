package com.learningAboutAutoMobile.learningAboutAutoMobile.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;
    
    // Default constructor
    public Visit() {
        this.timestamp = LocalDateTime.now();
    }
    
    // Constructor with fields
    public Visit(User user, Video video) {
        this.user = user;
        this.video = video;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Video getVideo() {
        return video;
    }
    
    public void setVideo(Video video) {
        this.video = video;
    }
} 