package com.whale.siki_shop_app.controller;

import com.whale.siki_shop_app.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/${api.prefix}/category")
public class CategoryController {

    @GetMapping(value = "")
    public ResponseEntity<String> getCategories(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit
    ) {
        return ResponseEntity.ok(String.format("getAllCategory %d page, %d limit", page, limit));
    }

    @PostMapping(value = "")
    public ResponseEntity<?> insertCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok("This is insertCategory \n," + categoryDTO.toString());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok("This is updateCategory with " + id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok("This is deleteCategory with " + id);
    }
}
