package com.learningAboutAutoMobile.learningAboutAutoMobile.controller;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                              @RequestParam String email,
                              @RequestParam String password,
                              RedirectAttributes redirectAttributes) {
        
        // Check if username already exists
        if (userRepository.findByUsername(username).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return "redirect:/register";
        }
        
        // Create new user
        User newUser = new User(username, email, password, false);
        userRepository.save(newUser);
        
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        
        // Find user by username
        var userOptional = userRepository.findByUsername(username);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Simple password check (in production, use proper password hashing)
            if (user.getPassword().equals(password)) {
                session.setAttribute("currentUser", user);
                redirectAttributes.addFlashAttribute("success", "Login successful!");
                return "redirect:/";
            }
        }
        
        redirectAttributes.addFlashAttribute("error", "Invalid username or password!");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Logged out successfully!");
        return "redirect:/";
    }
} 