package com.manager.library.controller;

import com.manager.library.domain.Book;
import com.manager.library.dtos.BookRequestDTO;
import com.manager.library.dtos.GoogleBooksDetailResponseDTO;
import com.manager.library.service.BookService;

import com.manager.library.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDTO bookRequestDTO) {

        Book newBook = bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable UUID id) {

        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody BookRequestDTO bookRequestDTO) {

        Book updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(updatedBook);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {

        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<Book>> listBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Book> bookPage = bookService.listBooks(page, size);
        return ResponseEntity.ok(bookPage.getContent());

    }

    @GetMapping("/category/{userId}")
    public ResponseEntity<List<Book>> getRecommendedBooks(@PathVariable UUID userId){

        return ResponseEntity.ok(recommendationService.getRecommendedBooks(userId));

    }

    @PostMapping("/google-api")
    public ResponseEntity<Book> addBook(@RequestParam String title) {

        return ResponseEntity.ok(bookService.addBookByTitle(title));

    }

    @GetMapping("/google-api")
    public ResponseEntity<GoogleBooksDetailResponseDTO> getBookInformation(@RequestParam String title) {

        return ResponseEntity.ok(bookService.getInformationByTitleFromGoogleApi(title));

    }
}