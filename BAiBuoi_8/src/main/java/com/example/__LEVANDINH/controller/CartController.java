package com.example.__LEVANDINH.controller;

import com.example.__LEVANDINH.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("totalAmount", cartService.getAmount());
        model.addAttribute("count", cartService.getCount());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {
        cartService.add(id);
        return "redirect:/cart/view"; // Sau khi thêm, nhảy vào trang giỏ hàng luôn
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
        return "redirect:/cart/view";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long id, @RequestParam int qty) {
        cartService.update(id, qty);
        return "redirect:/cart/view";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart/view";
    }
}
