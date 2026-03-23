package com.example.__LEVANDINH.controller;

import com.example.__LEVANDINH.entity.Product;
import com.example.__LEVANDINH.repository.CategoryRepository;
import com.example.__LEVANDINH.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryRepository categoryRepo;

    @GetMapping("/products")
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(required = false) Long categoryId,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "asc") String sort) {

        Page<Product> productPage = productService.getProducts(keyword, categoryId, page, sort);

        model.addAttribute("products", productPage.getContent()); // Danh sách SP trang hiện tại
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("sort", sort);
        model.addAttribute("categories", categoryRepo.findByIsDeletedFalse()); // Để hiện Dropdown lọc

        return "product-list";
    }
}
