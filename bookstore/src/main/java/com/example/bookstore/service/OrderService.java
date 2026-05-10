package com.example.bookstore.service;

import com.example.bookstore.model.CartItem;
import com.example.bookstore.model.Order;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(String sessionId, String username, String address, String paymentMethod) {
        List<CartItem> items = new ArrayList<>(cartService.getCart(sessionId));
        if (items.isEmpty()) throw new IllegalStateException("Cart is empty");

        double total = cartService.getCartTotal(sessionId);
        
        // Items in cart currently have username=sessionId. 
        // When placing order, we can keep them as is or link them to the order.
        // The Order model has @OneToMany with CartItem.
        
        Order order = new Order(null, username, items, total, address, paymentMethod);
        Order savedOrder = orderRepository.save(order);
        
        cartService.clearCart(sessionId);
        return savedOrder;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUser(String username) {
        return orderRepository.findByUsername(username).stream()
            .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
            .toList();
    }
}
