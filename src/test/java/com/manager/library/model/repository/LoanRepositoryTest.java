package com.manager.library.model.repository;

import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Should save a loan")
    public void shouldSaveALoan() {

        Loan loan = Loan.builder()
                .book(Book.builder()
                        .title("Clean Code")
                        .author("Robert C. Martin")
                        .isbn("9780132350884").build())
                .user(Users.builder()
                        .name("João Rolo")
                        .email("joao.rollo1@gmail.com")
                        .phone("44933000755").build()).build();

        usersRepository.save(loan.getUser());
        bookRepository.save(loan.getBook());
        loanRepository.save(loan);

        assertNotNull(loan.getId());
        assertEquals("Clean Code", loan.getBook().getTitle());
        assertEquals("Robert C. Martin", loan.getBook().getAuthor());
        assertEquals("9780132350884", loan.getBook().getIsbn());
        assertEquals("João Rolo", loan.getUser().getName());

    }

    @Test
    @DisplayName("Should delete a loan")
    public void shouldDeleteALoan() {

        Loan loan = Loan.builder()
                .book(Book.builder()
                        .title("Clean Code")
                        .author("Robert C. Martin")
                        .isbn("9780132350884").build())
                .user(Users.builder()
                        .name("João Rolo")
                        .email("joao.rollo1@gmail.com")
                        .phone("44933000755").build()).build();

        usersRepository.save(loan.getUser());
        bookRepository.save(loan.getBook());
        loanRepository.save(loan);

        loanRepository.delete(loan);

        assertTrue(loanRepository.findById(loan.getId()).isEmpty());

    }


}