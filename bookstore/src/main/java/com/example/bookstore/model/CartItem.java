package com.example.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private Long bookId;
    private String title;
    private String author;
    private String imageUrl;
    private double price;
    private int quantity;

    public CartItem() {}

    public CartItem(Long id, String username, Long bookId, String title, String author, String imageUrl, double price, int quantity) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public double getSubtotal() {
        return price * quantity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
