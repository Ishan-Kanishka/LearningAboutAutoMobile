package com.learningAboutAutoMobile.learningAboutAutoMobile.repository;

import com.learningAboutAutoMobile.learningAboutAutoMobile.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ghVideoRepository extends JpaRepository<Video, Long> {
} 