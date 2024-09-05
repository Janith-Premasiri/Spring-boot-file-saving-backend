package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}