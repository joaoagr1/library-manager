package com.manager.library.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;
@Builder
public record LoanRequestDTO(
        UUID userId,
        UUID bookId,

        @NotNull(message = "Return date is required")
        @Future(message = "Return date must be a future date")
        LocalDate returnDate

) {
}
