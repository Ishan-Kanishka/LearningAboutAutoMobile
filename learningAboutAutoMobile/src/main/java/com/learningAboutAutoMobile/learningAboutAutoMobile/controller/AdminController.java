package com.learningAboutAutoMobile.learningAboutAutoMobile.controller;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Video;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/add-video")
    public String showAddVideoForm(HttpSession session, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("currentUser");
        
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Please login first!");
            return "redirect:/login";
        }
        
        if (!currentUser.isAdmin()) {
            redirectAttributes.addFlashAttribute("error", "Access denied! Admin privileges required.");
            return "redirect:/";
        }
        
        return "admin-add-video";
    }

    @PostMapping("/add-video")
    public String addVideo(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String youtubeId,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        
        User currentUser = (User) session.getAttribute("currentUser");
        
        if (currentUser == null || !currentUser.isAdmin()) {
            redirectAttributes.addFlashAttribute("error", "Access denied!");
            return "redirect:/";
        }
        
        // Create and save new video
        Video newVideo = new Video(title, description, youtubeId);
        videoRepository.save(newVideo);
        
        redirectAttributes.addFlashAttribute("success", "Video added successfully!");
        return "redirect:/admin/add-video";
    }
} 