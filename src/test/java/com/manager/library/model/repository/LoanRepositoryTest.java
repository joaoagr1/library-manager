package com.manager.library.model.repository;

import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.domain.Users;
import com.manager.library.model.enums.Category;
import com.manager.library.model.enums.LoanStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
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

    @Test
    @DisplayName("Should list all Loans")
    public void shouldListAllLoans() {


        Loan loan1 = Loan.builder().loanDate(LocalDate.now())
                .returnDate(LocalDate.of(2025, 9, 18))
                .status(LoanStatus.ACTIVE)
                .book(Book.builder()
                        .title("Clean Code")
                        .author("Robert C. Martin")
                        .isbn("9780132350884")
                        .category(Category.BIOGRAPHY)
                        .publicationDate(Year.of(2019))
                .build())
                .user(Users.builder()
                        .name("João Rolo")
                        .email("joao.rollor1@gmail.com")
                        .phone("44933000755")
                        .registrationDate(LocalDate.now())
                        .build())
                .build();


        Loan loan2 = Loan.builder().loanDate(LocalDate.now())
                .returnDate(LocalDate.of(2025, 9, 18))
                .status(LoanStatus.ACTIVE)
                .book(Book.builder()
                        .title("Lord of the Rings")
                        .author("Tolkien")
                        .isbn("00000000000").
                        category(Category.ART)
                        .publicationDate(Year.of(
                                Integer.parseInt("2019")))
                        .build())
                .user(Users.builder()
                        .name("Marcelo da Silva")
                        .email("marcelo.silva@gmail.com")
                        .phone("44933340755")
                        .registrationDate(LocalDate.now())
                        .build())
                .returnDate(LocalDate.of(2025, 9, 18))
                .build();


        usersRepository.save(loan1.getUser());
        bookRepository.save(loan1.getBook());
        loanRepository.save(loan1);

        usersRepository.save(loan2.getUser());
        bookRepository.save(loan2.getBook());
        loanRepository.save(loan2);


        List<Loan> loans = loanRepository.findAll();
        assertEquals(2, loans.size(), "There should be exactly 2 loans in the repository.");


    }


}