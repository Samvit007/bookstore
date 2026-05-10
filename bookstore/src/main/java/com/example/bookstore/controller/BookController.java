package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genre) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(bookService.searchBooks(search));
        }
        if (genre != null && !genre.isBlank()) {
            return ResponseEntity.ok(bookService.getBooksByGenre(genre));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genres")
    public ResponseEntity<List<String>> getAllGenres() {
        return ResponseEntity.ok(bookService.getAllGenres());
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String username = (String) body.get("username");
        String comment = (String) body.get("comment");
        Integer rating = (Integer) body.get("rating");

        if (username == null || username.isBlank() || comment == null || comment.isBlank() || rating == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username, comment and rating are required"));
        }
        if (rating < 1 || rating > 5) {
            return ResponseEntity.badRequest().body(Map.of("error", "Rating must be between 1 and 5"));
        }

        Review review = bookService.addReview(id, username, comment, rating);
        if (review == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(book.get().getReviews());
    }
}
