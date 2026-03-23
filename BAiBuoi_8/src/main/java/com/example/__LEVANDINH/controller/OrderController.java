package com.example.__LEVANDINH.controller;

import com.example.__LEVANDINH.entity.Order;
import com.example.__LEVANDINH.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model, RedirectAttributes ra) {
        try {
            Order order = orderService.checkout();
            model.addAttribute("order", order);
            return "checkout-success";
        } catch (Exception e) {
            e.printStackTrace();
            // Gửi thông báo lỗi về trang giỏ hàng
            ra.addFlashAttribute("error", "Lỗi DB: " + e.getMessage());
            return "redirect:/cart/view";
        }
    }
}
