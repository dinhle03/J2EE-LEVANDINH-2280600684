package com.example.__LEVANDINH.service;

import com.example.__LEVANDINH.entity.Product;
import com.example.__LEVANDINH.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getProducts(String keyword, Long categoryId, int page, String sortDir) {
        // Mặc định sắp xếp theo giá, hướng sắp xếp (asc/desc) truyền từ controller
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by("price").ascending() : Sort.by("price").descending();
        Pageable pageable = PageRequest.of(page, 5, sort); // 5 sản phẩm mỗi trang

        if (categoryId != null && categoryId > 0) {
            return productRepository.findByCategoryIdAndNameContainingIgnoreCaseAndIsDeletedFalse(categoryId, keyword, pageable);
        }
        return productRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(keyword, pageable);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        Product p = getById(id);
        p.setDeleted(true); // Xóa mềm
        productRepository.save(p);
    }
}