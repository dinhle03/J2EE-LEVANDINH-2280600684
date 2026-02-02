package com.example.bai2.controller;

import com.example.bai2.model.Category;
import com.example.bai2.service.CategoryService;
import com.example.bai2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "category/categorys";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/create")
    public String create(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/create";
        }
        categoryService.add(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Category category = categoryService.get(id);
        if (category == null) return "redirect:/categories";
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category/edit";
        }
        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (productService.isCategoryUsed(id)) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Không thể xóa! Có sản phẩm đang thuộc danh mục này.");
            return "redirect:/categories";
        }

        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công!");
        return "redirect:/categories";
    }
}