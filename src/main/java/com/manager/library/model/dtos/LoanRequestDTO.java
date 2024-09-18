package com.manager.library.model.dtos;

import com.manager.library.model.enums.LoanStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record LoanRequestDTO(
        UUID userId,
        UUID bookId,

        @PastOrPresent(message = "Loan date must be a past or present date")
        @NotNull(message = "Loan date is required")
        LocalDate loanDate,

        @NotNull(message = "Return date is required")
        @Future(message = "Return date must be a future date")
        LocalDate returnDate

) {
}
