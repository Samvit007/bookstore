package com.example.bookstore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<CartItem> items;
    
    private double total;
    private String status;
    private String createdAt;
    
    @Column(columnDefinition = "TEXT")
    private String address;
    
    private String paymentMethod;

    public Order() {}

    public Order(Long id, String username, List<CartItem> items, double total, String address, String paymentMethod) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.total = total;
        this.status = "CONFIRMED";
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"));
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
