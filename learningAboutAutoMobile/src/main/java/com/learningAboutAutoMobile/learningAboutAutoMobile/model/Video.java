package com.learningAboutAutoMobile.learningAboutAutoMobile.model;

import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "youtube_id", nullable = false)
    private String youtubeId;
    
    // Default constructor
    public Video() {}
    
    // Constructor with fields
    public Video(String title, String description, String youtubeId) {
        this.title = title;
        this.description = description;
        this.youtubeId = youtubeId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getYoutubeId() {
        return youtubeId;
    }
    
    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }
} 