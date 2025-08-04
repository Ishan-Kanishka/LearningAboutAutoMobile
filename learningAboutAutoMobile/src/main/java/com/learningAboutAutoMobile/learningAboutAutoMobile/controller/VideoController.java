package com.learningAboutAutoMobile.learningAboutAutoMobile.controller;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Video;
import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Visit;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.VideoRepository;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("")
    public String listVideos(Model model, HttpSession session) {
        List<Video> videos = videoRepository.findAll();
        model.addAttribute("videos", videos);
        
        // Get current user from session
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        
        return "video-list";
    }

    @GetMapping("/{id}")
    public String showVideo(@PathVariable Long id, Model model, HttpSession session) {
        var videoOptional = videoRepository.findById(id);
        
        if (videoOptional.isPresent()) {
            Video video = videoOptional.get();
            model.addAttribute("video", video);
            
            // Get current user from session
            User currentUser = (User) session.getAttribute("currentUser");
            model.addAttribute("currentUser", currentUser);
            
            // Record visit if user is logged in
            if (currentUser != null) {
                Visit visit = new Visit(currentUser, video);
                visitRepository.save(visit);
            }
            
            return "video-detail";
        } else {
            return "redirect:/videos";
        }
    }
} 