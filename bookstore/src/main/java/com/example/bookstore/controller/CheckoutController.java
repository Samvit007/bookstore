package com.example.bookstore.controller;

import com.example.bookstore.model.Order;
import com.example.bookstore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody Map<String, Object> body, HttpSession session) {
        String username = (String) body.getOrDefault("username", "guest");
        String address = (String) body.getOrDefault("address", "Not provided");
        String paymentMethod = (String) body.getOrDefault("paymentMethod", "Credit Card");

        if (address == null || address.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Shipping address is required"));
        }

        try {
            Order order = orderService.placeOrder(session.getId(), username, address, paymentMethod);
            return ResponseEntity.ok(Map.of(
                "message", "Order placed successfully!",
                "orderId", order.getId(),
                "order", order
            ));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrders(@RequestParam(defaultValue = "guest") String username) {
        return ResponseEntity.ok(orderService.getOrdersByUser(username));
    }
}
