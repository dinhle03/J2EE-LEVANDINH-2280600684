package com.example.bai2.service;

import com.example.bai2.model.Product;
import com.example.bai2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public void update(Product editProduct) {
        productRepository.save(editProduct);
    }

    public void updateImage(Product product, MultipartFile imageProduct) {
        if (!imageProduct.isEmpty()) {
            try {
                // Lưu vào thư mục "uploads" ở ngay thư mục gốc của project
                Path dirImages = Paths.get("uploads");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }

                String newFileName = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFileName);
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);

                product.setImage(newFileName); // Chỉ lưu tên file vào DB
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public boolean isCategoryUsed(int categoryId) {
        return productRepository.existsByCategoryId(categoryId);
    }
}