package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.CartItem;
import com.example.bookstore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartService {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCart(String sessionId) {
        return cartItemRepository.findByUsername(sessionId);
    }

    public CartItem addToCart(String sessionId, Long bookId, int quantity) {
        Optional<Book> bookOpt = bookService.getBookById(bookId);
        if (bookOpt.isEmpty()) return null;

        Book book = bookOpt.get();
        
        // Check if book already in cart for this session
        Optional<CartItem> existing = cartItemRepository.findByUsernameAndBookId(sessionId, bookId);
        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            return cartItemRepository.save(item);
        }

        CartItem item = new CartItem(
            null,
            sessionId,
            bookId,
            book.getTitle(),
            book.getAuthor(),
            book.getImageUrl(),
            book.getPrice(),
            quantity
        );
        return cartItemRepository.save(item);
    }

    public boolean removeFromCart(String sessionId, Long itemId) {
        if (cartItemRepository.existsById(itemId)) {
            cartItemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    public boolean updateQuantity(String sessionId, Long itemId, int quantity) {
        Optional<CartItem> itemOpt = cartItemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            CartItem item = itemOpt.get();
            if (quantity <= 0) {
                cartItemRepository.delete(item);
            } else {
                item.setQuantity(quantity);
                cartItemRepository.save(item);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public void clearCart(String sessionId) {
        cartItemRepository.deleteByUsername(sessionId);
    }

    public double getCartTotal(String sessionId) {
        return getCart(sessionId).stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public int getCartCount(String sessionId) {
        return getCart(sessionId).stream().mapToInt(CartItem::getQuantity).sum();
    }
}
