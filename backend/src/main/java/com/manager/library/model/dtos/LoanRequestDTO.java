package com.manager.library.model.dtos;

import com.manager.library.model.enums.LoanStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
