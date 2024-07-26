package com.whale.siki_shop_app.controller;

import com.whale.siki_shop_app.dto.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/${api.prefix}/order-detail")
public class OrderDetailController {

    @GetMapping(value = "/index/{order_id}")
    public ResponseEntity<?> indexOrderDetail(
            @Valid @PathVariable(name = "order_id") Integer orderId
    ) {
        try {
            return ResponseEntity.ok("List of order");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-order-detail-by-id/{id}")
    public ResponseEntity<?> getOrderDetailById(
            @Valid @PathVariable(name = "id") Integer orderDetailId
    ) {
        try {
            return ResponseEntity.ok("OrderDetail with id: " + orderDetailId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/store")
    public ResponseEntity<?> storeOrderDetail(
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            return ResponseEntity.ok("OrderDetail has been stored successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody OrderDetailDTO orderDetailDTO,
            BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                       .stream()
                       .map(FieldError::getDefaultMessage)
                       .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            return ResponseEntity.ok("OrderDetail with id: " + id + " has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
