package com.example.__LEVANDINH.service;

import com.example.__LEVANDINH.entity.Product;
import com.example.__LEVANDINH.model.CartItem;
import com.example.__LEVANDINH.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope // Giỏ hàng lưu trong Session của từng người dùng
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final Map<Long, CartItem> maps = new HashMap<>(); // Lưu trữ sản phẩm trong giỏ

    // Thêm sản phẩm vào giỏ
    public void add(Long id) {
        CartItem item = maps.get(id);
        if (item == null) {
            Product p = productRepository.findById(id).orElse(null);
            if (p != null) {
                item = new CartItem(p.getId(), p.getName(), p.getImage(), p.getPrice(), 1);
                maps.put(id, item);
            }
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    // Xóa sản phẩm khỏi giỏ
    public void remove(Long id) {
        maps.remove(id);
    }

    // Cập nhật số lượng
    public void update(Long id, int qty) {
        CartItem item = maps.get(id);
        if (item != null) {
            if (qty <= 0) {
                maps.remove(id);
            } else {
                item.setQuantity(qty);
            }
        }
    }

    // Xóa sạch giỏ hàng
    public void clear() {
        maps.clear();
    }

    // Lấy danh sách sản phẩm trong giỏ
    public Collection<CartItem> getItems() {
        return maps.values();
    }

    // Tính tổng số lượng
    public int getCount() {
        return maps.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    // Tính tổng tiền toàn bộ giỏ hàng
    public double getAmount() {
        return maps.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
