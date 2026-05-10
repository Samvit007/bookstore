package com.example.bookstore.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private double price;
    private String imageUrl;
    private String genre;
    private String isbn;
    private int pages;
    private String language;
    private double rating;
    private int reviewCount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "book")
    private List<Review> reviews = new ArrayList<>();

    public Book() {}

    public Book(Long id, String title, String author, String description, double price,
                String imageUrl, String genre, String isbn, int pages, String language, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.isbn = isbn;
        this.pages = pages;
        this.language = language;
        this.rating = rating;
        this.reviewCount = 0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviews.size(); }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public void addReview(Review review) {
        this.reviews.add(review);
        // Recalculate average rating
        this.rating = this.reviews.stream().mapToDouble(Review::getRating).average().orElse(this.rating);
    }
}
