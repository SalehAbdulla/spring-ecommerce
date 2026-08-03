package com.ecommerce.project.service;

import com.ecommerce.project.Exceptions.APIException;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static com.ecommerce.project.config.AppConstants.ALLOWED_TYPES;
import static com.ecommerce.project.config.AppConstants.MAX_IMAGE_SIZE;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
        // 1. Validate file size
        if (multipartFile.getSize() > MAX_IMAGE_SIZE) {
            throw new APIException("Image size must be less than 5MB");
        }

        Tika t = new Tika();
        // 2. Validate actual file type using magic bytes (not the client's Content-Type)
        String detectedType = t.detect(multipartFile.getInputStream());
        if (!ALLOWED_TYPES.contains(detectedType)) {
            throw new APIException("Only JPEG, PNG, GIF, and WEBP images are allowed");
        }

        // 3. Get original filename (for extension extraction only)
        String fileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        String newFileName = uuid.concat(fileName.substring(fileName.lastIndexOf('.')));
        String filePath = path + File.separator + newFileName;

        // 4. Upload
        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }
}
