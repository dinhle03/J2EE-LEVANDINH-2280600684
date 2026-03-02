package com.example.bai2.service;

import com.example.bai2.model.Category;
import com.example.bai2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category get(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void add(Category category) {
        categoryRepository.save(category);
    }

    public void update(Category editCategory) {
        categoryRepository.save(editCategory); // JPA tự hiểu là update nếu đã có ID
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}