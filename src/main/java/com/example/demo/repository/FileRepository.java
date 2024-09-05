package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}