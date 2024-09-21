package com.manager.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.manager.library.domain.Book;
import com.manager.library.domain.Loan;
import com.manager.library.domain.Users;
import com.manager.library.enums.Category;
import com.manager.library.repository.BookRepository;
import com.manager.library.repository.LoanRepository;
import com.manager.library.repository.UsersRepository;
import com.manager.library.service.RecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import java.util.UUID;

public class RecommendationServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    private UUID userId;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
    }

    @Test
    public void testGetRecommendedBooks_UserHasLoans() {
        Users user = new Users();
        user.setId(userId);

        Book book1 = new Book();
        book1.setCategory(Category.FICTION);
        Loan loan1 = new Loan();
        loan1.setBook(book1);

        Book book2 = new Book();
        book2.setCategory(Category.NON_FICTION);
        Loan loan2 = new Loan();
        loan2.setBook(book2);

        when(usersRepository.getReferenceById(userId)).thenReturn(user);
        when(loanRepository.findAllByUserId(userId)).thenReturn(Arrays.asList(loan1, loan2));
        when(bookRepository.findFirstByCategory(Category.SCIENCE)).thenReturn(new Book());

        List<Book> recommendedBooks = recommendationService.getRecommendedBooks(userId);

        assertEquals(1, recommendedBooks.size());
    }

    @Test
    public void testGetRecommendedBooks_UserHasNoLoans() {
        Users user = new Users();
        user.setId(userId);

        when(usersRepository.getReferenceById(userId)).thenReturn(user);
        when(loanRepository.findAllByUserId(userId)).thenReturn(Arrays.asList());

        Book recommendedBook = new Book();
        recommendedBook.setCategory(Category.FICTION);
        when(bookRepository.findFirstByCategory(Category.FICTION)).thenReturn(recommendedBook);

        List<Book> recommendedBooks = recommendationService.getRecommendedBooks(userId);

        assertEquals(1, recommendedBooks.size());
        assertEquals(Category.FICTION, recommendedBooks.get(0).getCategory());
    }

    @Test
    public void testGetRecommendedBooks_NoAvailableBooks() {
        Users user = new Users();
        user.setId(userId);

        Book book1 = new Book();
        book1.setCategory(Category.FICTION);
        Loan loan1 = new Loan();
        loan1.setBook(book1);

        when(usersRepository.getReferenceById(userId)).thenReturn(user);
        when(loanRepository.findAllByUserId(userId)).thenReturn(Arrays.asList(loan1));
        when(bookRepository.findFirstByCategory(Category.NON_FICTION)).thenReturn(null);

        List<Book> recommendedBooks = recommendationService.getRecommendedBooks(userId);

        assertEquals(0, recommendedBooks.size());
    }
}
