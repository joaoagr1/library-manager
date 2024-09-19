package com.manager.library.model.domain;


import com.manager.library.model.enums.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Loan")
@Table(name = "LOANS")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "User is required")
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Book is required")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @PastOrPresent(message = "Loan date must be a past or present date")
    @NotNull(message = "Loan date is required")
    private LocalDate loanDate = LocalDate.now();

    @NotNull(message = "Return date is required")
    @Future(message = "Return date must be a future date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private LoanStatus status = LoanStatus.ACTIVE;;

}
