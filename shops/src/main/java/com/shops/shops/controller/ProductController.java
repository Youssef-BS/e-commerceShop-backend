package com.shops.shops.controller;


import com.shops.shops.Response.ApiResponse;
import com.shops.shops.exceptions.ProductNotFoundException;
import com.shops.shops.exceptions.ResourceNotFoundException;
import com.shops.shops.model.Product;
import com.shops.shops.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/produxts")

public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("success", products));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById (@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId) ;
            return ResponseEntity.ok(new ApiResponse("success", product));

        }catch(ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage() , NOT_FOUND));
        }
    }
}
