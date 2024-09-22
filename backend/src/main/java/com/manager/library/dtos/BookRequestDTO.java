package com.manager.library.dtos;

import com.manager.library.enums.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Year;

@Builder
public record BookRequestDTO(

        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
        String title,

        @NotBlank(message = "Author is required")
        @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
        String author,

        @NotBlank(message = "ISBN is required")
        @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
        String isbn,

        @Past(message = "The publication date cannot be a future date")
        @NotNull(message = "Publication date is required")
        Year publicationDate,

        @NotBlank(message = "Category is required")
        @Enumerated(EnumType.STRING)
        Category category

) {
}
