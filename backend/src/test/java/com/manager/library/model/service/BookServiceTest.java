package com.manager.library.model.service;

import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.enums.Category;
import com.manager.library.model.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    private Book book;
    private BookRequestDTO bookRequestDTO;

    @BeforeEach
    void setUp() {
        book = Book.builder().id(UUID.randomUUID()).title("The Lord of the Rings").author("J.R.R. Tolkien").isbn("978-3-16-148410-0").publicationDate(Year.of(2019)).category(Category.BIOGRAPHY).build();

        bookRequestDTO = new BookRequestDTO("The Lord of the Rings", "J.R.R. Tolkien", "978-3-16-148410-0", Year.of(2019), Category.BIOGRAPHY);
    }

    @Test
    @DisplayName("Should create a book")
    void shouldCreateABook() {

        UUID id = UUID.randomUUID();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(bookRequestDTO);
        createdBook.setId(id);

        assertEquals(id, createdBook.getId(), "The book ID should match the UUID set");
        verify(bookRepository, times(1)).save(any(Book.class));

    }

    @Test
    @DisplayName("Should delete a book")
    void shouldDeleteABook() {
        UUID id = UUID.randomUUID();
        book.setId(id);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));


        bookService.deleteBook(id);


        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should update a book")
    void shouldUpdateABook() {
        UUID id = UUID.randomUUID();
        book.setId(id);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book updatedBook = bookService.updateBook(id, bookRequestDTO);

        assertEquals(id, updatedBook.getId(), "The book ID should match the UUID set");
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Should get a book by ID")
    void shouldGetABookById() {
        UUID id = UUID.randomUUID();
        book.setId(id);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(id);

        assertNotNull(foundBook, "The found book should not be null");
        assertEquals(id, foundBook.getId(), "The found book ID should match the UUID set");
        verify(bookRepository, times(1)).findById(id);
    }



}


