package com.manager.library.model.service;


import com.manager.library.model.adapter.BookAdapter;
import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book createBook(BookRequestDTO bookRequestDTO) {

        Book book = BookAdapter.toEntity(bookRequestDTO);
        return bookRepository.save(book);

    }


    public Optional<Book> getBookById(UUID id) {

        return bookRepository.findById(id);

    }


    public Book updateBook(UUID id, BookRequestDTO bookRequestDTO) {


        Book book = BookAdapter.toEntity(bookRequestDTO);
        book.setId(id);
        return bookRepository.save(book);

    }

    public void deleteBook(UUID id) {

        bookRepository.deleteById(id);
    }


    public Page<Book> listBooks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);

    }

}




