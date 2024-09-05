package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    private Long fileId;  // Foreign key referencing the file ID

    // Default constructor
    public ImageEntity() {}

    // Constructor with parameters
    public ImageEntity(String imageName, Long fileId) {
        this.imageName = imageName;
        this.fileId = fileId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}
