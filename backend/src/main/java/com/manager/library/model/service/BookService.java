package com.manager.library.model.service;

import com.manager.library.client.GoogleBooksClient;
import com.manager.library.model.adapter.BookAdapter;
import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.dtos.GoogleBooksResponse;
import com.manager.library.model.enums.Category;
import com.manager.library.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GoogleBooksClient googleBooksClient;

    public Book createBook(BookRequestDTO bookRequestDTO) {
        bookRepository.findByIsbn(bookRequestDTO.isbn()).ifPresent(book -> {
            throw new IllegalArgumentException("Book with ISBN already exists");
        });

        Book book = BookAdapter.toEntity(bookRequestDTO);
        return bookRepository.save(book);
    }

    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public Book updateBook(UUID id, BookRequestDTO bookRequestDTO) {
        bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Book book = BookAdapter.toEntity(bookRequestDTO);
        book.setId(id);
        return bookRepository.save(book);
    }

    public void deleteBook(UUID id) {
        bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        bookRepository.deleteById(id);
    }

    public Page<Book> listBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public Book addBookByTitle(String title) {

        GoogleBooksResponse response = googleBooksClient.getBookByIsbn("intitle:" + title);

        return bookRepository.save(BookAdapter.googleApiToBook(response));

    }
}