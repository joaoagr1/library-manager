package com.manager.library.repository;

import com.manager.library.domain.Book;
import com.manager.library.enums.Category;
import com.manager.library.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Year;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Should save a book")
    public void shouldSaveABook() {

        Book book = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884").build();

        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Clean Code");
        assertThat(book.getAuthor()).isEqualTo("Robert C. Martin");
        assertThat(book.getIsbn()).isEqualTo("9780132350884");

    }

    @Test
    @DisplayName("Should delete a book")
    public void shouldDeleteABook() {

        Book book = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884").build();

        bookRepository.save(book);

        bookRepository.delete(book);

        assertThat(bookRepository.findById(book.getId())).isEmpty();

    }

    @Test
    @DisplayName("Should find a book by isbn")
    public void shouldFindABookByIsbn() {

        Book book = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("9780132350884")
                .category(Category.ART)
                .publicationDate(Year.of(2019))
                .build();

        bookRepository.save(book);

        Optional<Book> bookFound = bookRepository.findByIsbn("9780132350884");

        assertThat(bookFound).isNotNull();
        assertThat(bookFound.get().getId()).isEqualTo(book.getId());
        assertThat(bookFound.get().getTitle()).isEqualTo(book.getTitle());
        assertThat(bookFound.get().getAuthor()).isEqualTo(book.getAuthor());
        assertThat(bookFound.get().getIsbn()).isEqualTo(book.getIsbn());

    }

    @Test
    @DisplayName("Should not find a book by isbn")
    public void shouldNotFindABookByIsbn() {

        Optional<Book> bookFound = bookRepository.findByIsbn("9780132350884");

        assertThat(bookFound).isEmpty();

    }


}