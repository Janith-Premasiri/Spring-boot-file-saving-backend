package com.example.demo.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.imageio.ImageIO;

import com.example.demo.model.FileEntity;
import com.example.demo.model.ImageEntity;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.ImageRepository;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ImageRepository imageRepository;

    // Define the directory where files will be saved
    private final String uploadDir = "C:/Users/asus/Desktop/janith file/";

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public FileEntity storeFile(MultipartFile file) throws IOException {
        // Create directory if it doesn't exist
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a unique file name to avoid conflicts
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);  // Automatically handles path separators

        // Save the file to the local directory
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Store metadata in the database
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setFileType(file.getContentType());
        fileEntity.setFilePath(filePath.toString());

        // Save metadata first to get the ID
        FileEntity savedFile = fileRepository.save(fileEntity);

        // If the file is a PDF, convert each page to an image and store them in a unique folder
        if ("application/pdf".equals(file.getContentType())) {
            convertPdfToImages(filePath.toString(), savedFile.getId());
        }

        return savedFile;
    }

    private void convertPdfToImages(String pdfFilePath, Long fileId) throws IOException {
        // Define a folder named after the unique ID
        String imagesDir = uploadDir + fileId + "/";
        File imagesDirectory = new File(imagesDir);
        if (!imagesDirectory.exists()) {
            imagesDirectory.mkdirs();
        }

        // Load the PDF document
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                // Render each page as an image
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                // Save each image as a file (PNG format) in the ID-named folder
                String imageFileName = String.format("page_%d.png", page + 1);
                File imageFile = new File(imagesDirectory, imageFileName);
                ImageIO.write(bufferedImage, "PNG", imageFile);

                // Save the image details in the ImageEntity table
                ImageEntity imageEntity = new ImageEntity(imageFileName, fileId);
                imageRepository.save(imageEntity);

                System.out.println("Saved: " + imageFile.getAbsolutePath());
            }
        }
    }
}
