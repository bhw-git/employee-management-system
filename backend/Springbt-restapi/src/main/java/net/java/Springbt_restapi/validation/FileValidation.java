package net.java.Springbt_restapi.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileValidation {
    private static final List<String> ALLOWED_TYPES = List.of(
            "image/png",
            "image/jpeg",
            "application/pdf",
            "application/json"
    );

    public static boolean isValid(MultipartFile file){
        if(file == null || file.isEmpty()){
            return false;
        }
        String contentType = file.getContentType();
        return contentType != null && ALLOWED_TYPES.contains(contentType);
    }
}