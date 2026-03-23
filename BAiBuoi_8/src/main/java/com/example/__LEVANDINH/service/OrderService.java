package com.example.__LEVANDINH.service;

import com.example.__LEVANDINH.entity.Order;
import com.example.__LEVANDINH.entity.OrderDetail;
import com.example.__LEVANDINH.entity.Product;
import com.example.__LEVANDINH.model.CartItem;
import com.example.__LEVANDINH.repository.OrderDetailRepository;
import com.example.__LEVANDINH.repository.OrderRepository;
import com.example.__LEVANDINH.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @Transactional
    public Order checkout() {
        Collection<CartItem> items = cartService.getItems();
        if (items.isEmpty()) throw new RuntimeException("Giỏ hàng đang trống!");

        // 1. Tạo đơn hàng mới
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now()); // Kiểm tra xem trường này trong DB có cho phép NULL không
        order.setTotalAmount(cartService.getAmount());

        // Lưu vào bảng orders
        Order savedOrder = orderRepository.save(order);

        // 2. Lưu chi tiết
        for (CartItem item : items) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);

            // Quan trọng: Phải tìm Product từ DB để tránh lỗi Transient
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            detail.setProduct(product);
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());

            orderDetailRepository.save(detail);
        }

        cartService.clear();
        return savedOrder;
    }
}
