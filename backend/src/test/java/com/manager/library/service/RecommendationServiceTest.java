package com.manager.library.service;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
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

import java.util.*;

class RecommendationServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    private UUID userId;
    private Users user;
    private Book book1;
    private Book book2;
    private Loan loan1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();
        user = new Users();
        user.setId(userId);

        book1 = new Book();
        book1.setCategory(Category.FICTION);

        book2 = new Book();
        book2.setCategory(Category.SCIENCE);

        loan1 = new Loan();
        loan1.setBook(book1);
    }

    @Test
    void shouldReturnRecommendedBooks() {
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));
        when(loanRepository.findAllByUserId(userId)).thenReturn(Collections.singletonList(loan1));
        when(bookRepository.findFirstByCategory(Category.SCIENCE)).thenReturn(book2);
        when(bookRepository.findFirstByCategory(Category.NON_FICTION)).thenReturn(null);

        List<Book> recommendedBooks = recommendationService.getRecommendedBooks(userId);

        assertNotNull(recommendedBooks);
        assertEquals(1, recommendedBooks.size());
        assertTrue(recommendedBooks.contains(book2));

        verify(usersRepository, times(1)).findById(userId);
        verify(loanRepository, times(1)).findAllByUserId(userId);
        verify(bookRepository, times(1)).findFirstByCategory(Category.SCIENCE);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(usersRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            recommendationService.getRecommendedBooks(userId);
        });

        assertEquals("User not found", exception.getMessage());

        verify(usersRepository, times(1)).findById(userId);
        verify(loanRepository, never()).findAllByUserId(userId);
    }

    @Test
    void shouldReturnEmptyListWhenNoRecommendedBooks() {
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));
        when(loanRepository.findAllByUserId(userId)).thenReturn(Collections.singletonList(loan1));
        when(bookRepository.findFirstByCategory(any(Category.class))).thenReturn(null);

        List<Book> recommendedBooks = recommendationService.getRecommendedBooks(userId);

        assertNotNull(recommendedBooks);
        assertTrue(recommendedBooks.isEmpty());

        verify(usersRepository, times(1)).findById(userId);
        verify(loanRepository, times(1)).findAllByUserId(userId);
    }
}