package com.whale.siki_shop_app.controller;

import com.whale.siki_shop_app.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/product")
public class ProductController {

    @GetMapping(value = "")
    public ResponseEntity<String> getProducts(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit
    ) {
        return ResponseEntity.ok(String.format("getProducts %d page, %d limit", page, limit));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(String.format("getProductById %d", id));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @Valid @ModelAttribute ProductDTO productDTO,
            BindingResult bindingResult
    ) {
        try {
//            Check Loi
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            List<MultipartFile> listImage = productDTO.getListImage();
            listImage = (listImage == null) ? new ArrayList<MultipartFile>() : listImage;
            for (MultipartFile file : listImage) {
                if (file != null) {
                    if (file.getSize() == 0) {
                        continue;
                    }
//                Check kich thuoc File va Dinh Dang
                    if (file.getSize() > 10 * Math.pow(1024, 2)) {
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                                .body("File is too large! Maximum size is 10MB");
                    }
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")) {
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                                .body("File must be an image");
                    }
//                Luu File and cap nhat file trong DTO
                    String filename = storeFile(file);
                }
            }
            return ResponseEntity.ok("This is createProduct");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
//        Lay ten file
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        Them UUID vao truoc ten file de dam bao khong bi trung ten
        String uniqueFileName = UUID.randomUUID().toString() + "_" + filename;
//        Duong dan thu muc luu file
        java.nio.file.Path upLoadDir = Paths.get("uploads");
//        Check thu muc va tao neu ko ton tai
        if (!Files.exists(upLoadDir)) {
            Files.createDirectories(upLoadDir);
        }
//        Duong Dan day du den file
        java.nio.file.Path destination = Paths.get(upLoadDir.toString(), uniqueFileName);
//        Sao chep file vao thu muc
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok("This is updateProduct with " + id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok("This is deleteProduct with " + id);
    }
}
