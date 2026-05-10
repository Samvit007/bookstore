package com.example.bookstore.controller;

import com.example.bookstore.model.CartItem;
import com.example.bookstore.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart(HttpSession session) {
        String sessionId = session.getId();
        List<CartItem> items = cartService.getCart(sessionId);
        double total = cartService.getCartTotal(sessionId);
        int count = cartService.getCartCount(sessionId);
        return ResponseEntity.ok(Map.of(
            "items", items,
            "total", total,
            "count", count
        ));
    }

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> body, HttpSession session) {
        String sessionId = session.getId();
        Long bookId = Long.valueOf(body.get("bookId").toString());
        int quantity = body.containsKey("quantity") ? Integer.parseInt(body.get("quantity").toString()) : 1;

        CartItem item = cartService.addToCart(sessionId, bookId, quantity);
        if (item == null) return ResponseEntity.badRequest().body(Map.of("error", "Book not found"));

        return ResponseEntity.ok(Map.of(
            "message", "Added to cart",
            "item", item,
            "cartCount", cartService.getCartCount(sessionId)
        ));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long itemId, HttpSession session) {
        boolean removed = cartService.removeFromCart(session.getId(), itemId);
        if (!removed) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of(
            "message", "Removed from cart",
            "total", cartService.getCartTotal(session.getId()),
            "count", cartService.getCartCount(session.getId())
        ));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long itemId,
                                             @RequestBody Map<String, Object> body,
                                             HttpSession session) {
        int quantity = Integer.parseInt(body.get("quantity").toString());
        boolean updated = cartService.updateQuantity(session.getId(), itemId, quantity);
        if (!updated) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of(
            "message", "Updated",
            "total", cartService.getCartTotal(session.getId()),
            "count", cartService.getCartCount(session.getId())
        ));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Integer>> getCartCount(HttpSession session) {
        return ResponseEntity.ok(Map.of("count", cartService.getCartCount(session.getId())));
    }
}
