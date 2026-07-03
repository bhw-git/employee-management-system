package net.java.Springbt_restapi.service.impl;

import net.java.Springbt_restapi.service.FileStorageService;
import net.java.Springbt_restapi.validation.FileValidation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final String UPLOAD_DIR = "uploads/profile/";

    public boolean validateFileType(MultipartFile file){
        return FileValidation.isValid(file);
    }

    @Override
    public String save(MultipartFile file) {
        try{
            if(!FileValidation.isValid(file)){ throw new RuntimeException("Invalid file type"); }
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("FileStorage save(): " + filePath);
            System.out.println("UniqueFileName: " + uniqueFileName);
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

    }
}
