package com.learningAboutAutoMobile.learningAboutAutoMobile.repository;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.User;
import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    
    @Query("SELECT v.user, COUNT(v) FROM Visit v GROUP BY v.user ORDER BY COUNT(v) DESC")
    List<Object[]> findTopUsersByVisitCount();
    
    @Query("SELECT COUNT(v) FROM Visit v WHERE v.user = :user")
    Long countVisitsByUser(@Param("user") User user);
} 