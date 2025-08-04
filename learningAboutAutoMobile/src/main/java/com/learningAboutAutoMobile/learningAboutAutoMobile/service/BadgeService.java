package com.learningAboutAutoMobile.learningAboutAutoMobile.service;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeService {
    
    @Autowired
    private VisitRepository visitRepository;
    
    public List<User> getMostVisitedUsers() {
        List<Object[]> results = visitRepository.findTopUsersByVisitCount();
        List<User> topUsers = new ArrayList<>();
        
        // Get top 3 users
        for (int i = 0; i < Math.min(3, results.size()); i++) {
            Object[] result = results.get(i);
            User user = (User) result[0];
            topUsers.add(user);
        }
        
        return topUsers;
    }
} 