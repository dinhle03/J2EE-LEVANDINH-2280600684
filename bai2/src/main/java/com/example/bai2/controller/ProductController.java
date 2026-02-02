package com.example.bai2.controller;

import com.example.bai2.model.Category;
import com.example.bai2.model.Product;
import com.example.bai2.service.CategoryService;
import com.example.bai2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String Index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String Create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String Create(@Valid Product newProduct,
                         BindingResult result,
                         @RequestParam("imageProduct") MultipartFile imageProduct,
                         Model model) {
        // Kiểm tra nếu không chọn ảnh (Lỗi bạn gặp phải)
        if (imageProduct.isEmpty()) {
            result.rejectValue("image", "error.product", "Vui lòng chọn hình ảnh cho sản phẩm");
        }

        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }

        productService.updateImage(newProduct, imageProduct);

        // Gán đầy đủ Category (bao gồm cả Name) trước khi add
        Category selectedCategory = categoryService.get(newProduct.getCategory().getId());
        newProduct.setCategory(selectedCategory);

        productService.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String Edit(@PathVariable int id, Model model) {
        Product find = productService.get(id);
        if (find == null) {
            return "error/404"; // Trang lỗi tùy chỉnh
        }
        model.addAttribute("product", find);
        model.addAttribute("categories", categoryService.getAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String Edit(@Valid Product editProduct,
                       BindingResult result,
                       @RequestParam("imageProduct") MultipartFile imageProduct,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", editProduct);
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        // Xử lý ảnh nếu người dùng thay đổi ảnh mới
        if (!imageProduct.isEmpty()) {
            productService.updateImage(editProduct, imageProduct);
        } else {
            // Giữ lại ảnh cũ nếu không chọn ảnh mới
            Product oldProduct = productService.get(editProduct.getId());
            editProduct.setImage(oldProduct.getImage());
        }

        // QUAN TRỌNG: Gán lại đầy đủ thông tin Category từ Service để có Name hiển thị
        Category selectedCategory = categoryService.get(editProduct.getCategory().getId());
        editProduct.setCategory(selectedCategory);

        productService.update(editProduct);
        return "redirect:/products";
    }

    // Thêm tính năng Xóa
    @GetMapping("/delete/{id}")
    public String Delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        model.addAttribute("listproduct", productService.searchProduct(name));
        return "product/products";
    }


}
