package com.example.bookstore.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;
    
    private String username;
    
    @Column(columnDefinition = "TEXT")
    private String comment;
    
    private int rating;
    private String createdAt;

    public Review() {}

    public Review(Long id, Book book, String username, String comment, int rating) {
        this.id = id;
        this.book = book;
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public Long getBookId() { return book != null ? book.getId() : null; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
