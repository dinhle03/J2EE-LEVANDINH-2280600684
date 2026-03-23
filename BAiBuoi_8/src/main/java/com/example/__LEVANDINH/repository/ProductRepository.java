package com.example.__LEVANDINH.repository;

import com.example.__LEVANDINH.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Tìm kiếm + Phân trang (Không lọc Category)
    Page<Product> findByNameContainingIgnoreCaseAndIsDeletedFalse(String keyword, Pageable pageable);

    // Tìm kiếm + Lọc theo Category + Phân trang
    Page<Product> findByCategoryIdAndNameContainingIgnoreCaseAndIsDeletedFalse(Long categoryId, String keyword, Pageable pageable);
}