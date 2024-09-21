package com.manager.library.model.service;

import com.manager.library.client.GoogleBooksClient;
import com.manager.library.model.domain.Book;
import com.manager.library.model.dtos.BookRequestDTO;
import com.manager.library.model.dtos.GoogleBooksResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GoogleBooksClient googleBooksClient;

    @InjectMocks
    private BookService bookService;
    private GoogleBooksResponseDTO googleBooksResponseDTO;

    private Book book;
    private BookRequestDTO bookRequestDTO;

    UUID  bookId = UUID.randomUUID();

    @BeforeEach
    void setUp() {

        googleBooksResponseDTO = new GoogleBooksResponseDTO();


        GoogleBooksResponseDTO.Item item = new GoogleBooksResponseDTO.Item();
        googleBooksResponseDTO.setItems(List.of(item));

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


    @Test
    void shouldThrowExceptionWhenBookWithIsbnAlreadyExists() {
        Book existingBook = new Book();
        existingBook.setIsbn("1234567890");

        when(bookRepository.findByIsbn(bookRequestDTO.isbn())).thenReturn(Optional.of(existingBook));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.createBook(bookRequestDTO)
        );

        assertEquals("Book with ISBN already exists", exception.getMessage());

        verify(bookRepository, times(1)).findByIsbn(bookRequestDTO.isbn());
        verify(bookRepository, never()).save(any(Book.class));
    }


    @Test
    void shouldThrowExceptionWhenBookNotFound() {
      UUID  bookId = UUID.randomUUID();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.getBookById(bookId)
        );

        assertEquals("Book not found", exception.getMessage());

        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void shouldThrowExceptionWhenBookToDeleteNotFound() {

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.deleteBook(bookId)
        );

        assertEquals("Book not found", exception.getMessage());

        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, never()).deleteById(bookId);
    }


    @Test
    public void testAddBookByTitle_BookNotFound() {

        String title = "Nonexistent Book";
        GoogleBooksResponseDTO response = new GoogleBooksResponseDTO();
        response.setItems(new ArrayList<>());


        when(googleBooksClient.getBookByIsbn("intitle:" + title)).thenReturn(response);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.addBookByTitle(title);
        });
    }


    @Test
    public void testGetInformationByTitleFromGoogleApi_BookNotFound() {

        String title = "Nonexistent Book";
        GoogleBooksResponseDTO response = new GoogleBooksResponseDTO();
        response.setItems(new ArrayList<>());


        when(googleBooksClient.getBookByIsbn("intitle:" + title)).thenReturn(response);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.getInformationByTitleFromGoogleApi(title);
        });
    }


}


