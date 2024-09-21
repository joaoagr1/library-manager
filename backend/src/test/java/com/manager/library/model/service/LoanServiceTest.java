package com.manager.library.model.service;

import com.manager.library.model.adapter.LoanAdapter;
import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;

import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.LoanRequestDTO;
import com.manager.library.model.enums.LoanStatus;
import com.manager.library.model.exceptions.BookNotAvailableException;
import com.manager.library.model.exceptions.EntityNotFoundException;
import com.manager.library.model.repository.LoanRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanAdapter loanAdapter;

    @InjectMocks
    private LoanService loanService;

    private Loan loan;

    private Users user;
    private Book book;
   private LoanRequestDTO loanRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user =Users.builder()
                .id(UUID.randomUUID())
                .name("User1")
                .email("user1@example.com")
                .phone("12345")
                .build();

        book = Book.builder()
                .id(UUID.randomUUID())
                .title("The Great Gatsby")
                .build();

        loan = Loan.builder()
                .id(UUID.randomUUID())
                .user(user)
                .book(book)
                .loanDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(10))
                .status(LoanStatus.ACTIVE)
                .build();

        loanRequestDTO = LoanRequestDTO.builder()
                .userId(user.getId())
                .bookId(book.getId())
                .returnDate(LocalDate.now().plusDays(10))
                .build();
    }

    @Test
    @DisplayName("Should create a new loan using builder")
    void shouldCreateLoanUsingBuilder() {

        when(loanRepository.save(any())).thenReturn(loan);
        when(loanAdapter.toEntity(any(LoanRequestDTO.class))).thenReturn(loan);

        Loan createdLoan = loanService.createLoan(loanRequestDTO);

        assertNotNull(createdLoan, "The loan should not be null");
        assertEquals(loan.getId(), createdLoan.getId(), "The loan ID should match");
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    @DisplayName("Should update loan using builder")
    void shouldUpdateLoanUsingBuilder() {

        LoanRequestDTO loanRequestDTO = LoanRequestDTO.builder()
                .returnDate(LocalDate.of(2024, 9, 28))
                .build();


        when(loanAdapter.toEntity(any(LoanRequestDTO.class))).thenAnswer(invocation -> {
            LoanRequestDTO dto = invocation.getArgument(0);
            return Loan.builder()
                    .id(loan.getId())
                    .user(user)
                    .book(book)
                    .loanDate(LocalDate.now())
                    .returnDate(dto.returnDate())
                    .build();
        });

        UUID loanId = loan.getId();
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan savedLoan = invocation.getArgument(0);
            savedLoan.setStatus(LoanStatus.COMPLETED);
            return savedLoan;
        });


        Loan updatedLoan = loanService.updateLoan(loanId, loanRequestDTO);


        assertEquals(LoanStatus.COMPLETED, updatedLoan.getStatus(),
                "The loan status should be updated to COMPLETED");


        verify(loanRepository, times(1)).findById(loanId);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("Should delete loan using builder")
    void shouldDeleteLoanUsingBuilder() {
        UUID loanId = loan.getId();
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        loanService.deleteLoan(loanId);

        verify(loanRepository, times(1)).findById(loanId);
        verify(loanRepository, times(1)).deleteById(loanId);
    }

    @Test
    public void testCreateLoan_BookNotAvailable() {
        LoanRequestDTO loanRequestDTO = LoanRequestDTO.builder()
                .returnDate(LocalDate.of(2024, 9, 28))
                .build();

        when(loanService.hasActiveLoanForBook(loanRequestDTO.bookId())).thenReturn(true);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.createLoan(loanRequestDTO);
        });
    }

    @Test
    public void testGetLoanById_LoanNotFound() {
        UUID loanId = UUID.randomUUID();

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            loanService.getLoanById(loanId);
        });
    }

    @Test
    public void testUpdateLoan_LoanNotFound() {
        UUID loanId = UUID.randomUUID();
        LoanRequestDTO loanRequestDTO = LoanRequestDTO.builder()
                .returnDate(LocalDate.of(2024, 9, 28))
                .build();
        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            loanService.updateLoan(loanId, loanRequestDTO);
        });
    }

    @Test
    public void testDeleteLoan_LoanNotFound() {
        UUID loanId = UUID.randomUUID();

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            loanService.deleteLoan(loanId);
        });
    }

}