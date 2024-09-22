package com.manager.library.service;

import com.manager.library.client.GoogleBooksClient;
import com.manager.library.adapter.BookAdapter;
import com.manager.library.domain.Book;
import com.manager.library.dtos.BookRequestDTO;
import com.manager.library.dtos.GoogleBooksDetailResponseDTO;
import com.manager.library.dtos.GoogleBooksResponseDTO;
import com.manager.library.enums.LoanStatus;
import com.manager.library.repository.BookRepository;
import com.manager.library.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    private GoogleBooksClient googleBooksClient;
    private LoanRepository loansRepository;

    public Book createBook(BookRequestDTO bookRequestDTO) {

        bookRepository.findByIsbn(bookRequestDTO.isbn()).ifPresent(book -> {
            throw new IllegalArgumentException("Book with ISBN already exists");
        });

        return bookRepository.save(BookAdapter.toEntity(bookRequestDTO));

    }

    public Book getBookById(UUID id) {

        return bookRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Book not found"));

    }


    public Book updateBook(UUID id, BookRequestDTO bookRequestDTO) {

        bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Book book = BookAdapter.toEntity(bookRequestDTO);
        book.setId(id);
        return bookRepository.save(book);

    }

    public void deleteBook(UUID id) {

        bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        loansRepository.findAllByBookId(id).forEach(loan -> {
            if(loan.getStatus().equals(LoanStatus.ACTIVE)) {
                throw new IllegalArgumentException("Book is currently loaned");
            }
        });
        bookRepository.deleteById(id);

    }

    public Page<Book> listBooks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);

    }

    public Book addBookByTitle(String title) {

        GoogleBooksResponseDTO response = googleBooksClient.getBookByIsbn("intitle:" + title);

        if(response.getItems().isEmpty()) {
            throw new IllegalArgumentException("Book not found");
        }

        return bookRepository.save(BookAdapter.googleApiToBook(response));

    }

    public GoogleBooksDetailResponseDTO getInformationByTitleFromGoogleApi(String title) {

        try {

            GoogleBooksResponseDTO response = googleBooksClient.getBookByIsbn("intitle:" + title);

            return BookAdapter.from(response.getItems().get(0));

        } catch (Exception e) {

            throw new IllegalArgumentException("Book not found");

        }

    }



}


