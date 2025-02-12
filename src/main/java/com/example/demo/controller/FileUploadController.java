package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/images")
public class FileUploadController {

    private final String uploadDir = "C:/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        try {
            // Ensure the directory exists
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            // Generate unique filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            // Return correct image URL
            String imageUrl = "http://localhost:3060/uploads/" + fileName;
            return ResponseEntity.ok(imageUrl);

        } catch (IOException e) {
            e.printStackTrace(); // Print error in logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }
}

