package com.example.bookstore.repository;

import com.example.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUsername(String username);
    Optional<CartItem> findByUsernameAndBookId(String username, Long bookId);
    void deleteByUsername(String username);
}
