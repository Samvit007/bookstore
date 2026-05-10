package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Review;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        if (bookRepository.count() == 0) {
            initializeBooks();
        }
    }

    private void initializeBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(null, "The Great Gatsby", "F. Scott Fitzgerald",
            "A novel about the American dream set in the Jazz Age. It follows Jay Gatsby's obsessive pursuit of the beautiful Daisy Buchanan across a glittering, morally bankrupt New York social scene. A masterpiece of American literature that explores themes of wealth, class, love, and idealism.",
            10.99, "https://covers.openlibrary.org/b/id/8432515-L.jpg", "Classic Fiction", "978-0-7432-7356-5", 180, "English", 4.5));

        books.add(new Book(null, "To Kill a Mockingbird", "Harper Lee",
            "A profound novel about racial injustice and the loss of innocence in the American South. Through the eyes of young Scout Finch, we see her father Atticus defend a Black man falsely accused of a terrible crime in 1930s Alabama. Winner of the Pulitzer Prize.",
            14.99, "https://covers.openlibrary.org/b/id/8228691-L.jpg", "Classic Fiction", "978-0-06-112008-4", 281, "English", 4.8));

        books.add(new Book(null, "1984", "George Orwell",
            "A chilling dystopian masterpiece that introduced concepts like Big Brother, doublethink, and the Thought Police. Winston Smith lives in a totalitarian society where independent thinking is a crime punishable by death. A terrifying and prophetic vision of the future.",
            12.99, "https://covers.openlibrary.org/b/id/7222246-L.jpg", "Dystopian Fiction", "978-0-452-28423-4", 328, "English", 4.7));

        books.add(new Book(null, "Pride and Prejudice", "Jane Austen",
            "A timeless romantic novel that explores themes of love, marriage, and social class in early 19th-century England. The spirited Elizabeth Bennet and the proud Mr. Darcy navigate a course of misunderstandings and personal growth in one of literature's greatest love stories.",
            11.99, "https://covers.openlibrary.org/b/id/8739161-L.jpg", "Romance", "978-0-14-143951-8", 432, "English", 4.6));

        books.add(new Book(null, "The Hobbit", "J.R.R. Tolkien",
            "The enchanting prelude to The Lord of the Rings. Bilbo Baggins, a comfort-loving hobbit, is swept into an epic quest to reclaim a dwarf kingdom from a terrifying dragon. A timeless adventure of courage, friendship, and the unexpected discovery of inner strength.",
            15.99, "https://covers.openlibrary.org/b/id/8406786-L.jpg", "Fantasy", "978-0-618-00221-4", 310, "English", 4.9));

        books.add(new Book(null, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling",
            "The magical beginning of the legendary Harry Potter series. On his 11th birthday, Harry discovers he is a wizard and is whisked away to Hogwarts School of Witchcraft and Wizardry. A tale of friendship, courage, and the eternal battle between good and evil.",
            16.99, "https://covers.openlibrary.org/b/id/10110415-L.jpg", "Fantasy", "978-0-590-35340-3", 309, "English", 4.9));

        books.add(new Book(null, "The Alchemist", "Paulo Coelho",
            "A mystical fable about following your dreams. A young Andalusian shepherd boy named Santiago sets out on a journey to find a worldly treasure. Along the way he meets a series of characters who impart wisdom about listening to our hearts and following our destinies.",
            13.99, "https://covers.openlibrary.org/b/id/8392352-L.jpg", "Inspirational", "978-0-06-231609-7", 208, "English", 4.4));

        books.add(new Book(null, "The Catcher in the Rye", "J.D. Salinger",
            "A controversial and deeply influential coming-of-age novel narrated by the rebellious Holden Caulfield, who has just been expelled from prep school. His journey through New York City is a search for identity and authenticity in a world he sees as phony.",
            12.49, "https://covers.openlibrary.org/b/id/8231432-L.jpg", "Literary Fiction", "978-0-316-76948-0", 277, "English", 4.2));

        books.add(new Book(null, "Brave New World", "Aldous Huxley",
            "A prophetic and disturbing vision of a future society in which human beings are manufactured on an assembly line, conditioned to love their servitude, and kept docile with a happiness drug. A brilliant and terrifying satire about science, technology, and consumerism.",
            11.49, "https://covers.openlibrary.org/b/id/7998398-L.jpg", "Dystopian Fiction", "978-0-06-085052-4", 311, "English", 4.3));

        books.add(new Book(null, "The Lord of the Rings", "J.R.R. Tolkien",
            "The defining epic fantasy of the modern age. Frodo Baggins and his Fellowship of companions must undertake a perilous quest to destroy the One Ring and defeat the dark lord Sauron. A monumental work of imagination that has influenced virtually all epic fantasy that came after it.",
            24.99, "https://covers.openlibrary.org/b/id/9255566-L.jpg", "Fantasy", "978-0-618-64015-7", 1178, "English", 4.9));

        books.add(new Book(null, "The Da Vinci Code", "Dan Brown",
            "A fast-paced thriller that weaves together art history, secret societies, and religious controversy. Harvard symbologist Robert Langdon investigates a murder in the Louvre and uncovers a trail of clues hidden in the works of Leonardo da Vinci.",
            14.49, "https://covers.openlibrary.org/b/id/9255559-L.jpg", "Thriller", "978-0-385-50420-5", 454, "English", 4.1));

        books.add(new Book(null, "Sapiens: A Brief History of Humankind", "Yuval Noah Harari",
            "A groundbreaking narrative of humanity's creation and evolution. Exploring the ways biology and history have defined us, Harari challenges everything we thought we knew about being human — our thoughts, our actions, our power, and our future.",
            18.99, "https://covers.openlibrary.org/b/id/9255186-L.jpg", "Non-Fiction", "978-0-06-231609-7", 443, "English", 4.6));

        bookRepository.saveAll(books);

        // Add some sample reviews
        Book gatsby = bookRepository.findByTitleContaining("Gatsby").get(0);
        gatsby.addReview(new Review(null, gatsby, "BookLover42", "A timeless classic! The prose is simply beautiful.", 5));
        gatsby.addReview(new Review(null, gatsby, "ReadingRaven", "Fitzgerald's writing is absolutely mesmerizing.", 4));
        bookRepository.save(gatsby);

        Book mockingbird = bookRepository.findByTitleContaining("Mockingbird").get(0);
        mockingbird.addReview(new Review(null, mockingbird, "LiteraryMind", "One of the greatest novels ever written. A must-read.", 5));
        bookRepository.save(mockingbird);

        Book hobbit = bookRepository.findByTitleContaining("Hobbit").get(0);
        hobbit.addReview(new Review(null, hobbit, "FantasyFan99", "The adventure that started it all. Simply magical!", 5));
        bookRepository.save(hobbit);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContaining(query);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findAll().stream()
            .filter(b -> b.getGenre().equalsIgnoreCase(genre))
            .collect(Collectors.toList());
    }

    @Transactional
    public Review addReview(Long bookId, String username, String comment, int rating) {
        Optional<Book> bookOpt = getBookById(bookId);
        if (bookOpt.isEmpty()) return null;
        Book book = bookOpt.get();
        Review review = new Review(null, book, username, comment, rating);
        book.addReview(review);
        bookRepository.save(book);
        return review;
    }

    public List<String> getAllGenres() {
        return bookRepository.findAll().stream()
            .map(Book::getGenre)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
}
