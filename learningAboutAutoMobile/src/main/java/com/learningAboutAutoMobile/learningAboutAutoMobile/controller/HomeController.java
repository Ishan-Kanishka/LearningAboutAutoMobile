package com.learningAboutAutoMobile.learningAboutAutoMobile.controller;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Video;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.VideoRepository;
import com.learningAboutAutoMobile.learningAboutAutoMobile.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private BadgeService badgeService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // Get all videos
        List<Video> videos = videoRepository.findAll();
        model.addAttribute("videos", videos);
        
        // Get top 3 most visited users
        List<User> topUsers = badgeService.getMostVisitedUsers();
        model.addAttribute("topUsers", topUsers);
        
        // Get current user from session
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        
        return "home";
    }
} 