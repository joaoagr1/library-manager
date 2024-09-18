package com.manager.library.model.repository;

import com.manager.library.model.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

}