package com.example.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC Controller that serves the static frontend pages.
 * Routes "/" and "/index" → index.html via Spring's static resource handler.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    @GetMapping("/home")
    public String homeAlias() {
        return "redirect:/index.html";
    }

    @GetMapping("/books-page")
    public String booksPage() {
        return "redirect:/index.html";
    }
}
