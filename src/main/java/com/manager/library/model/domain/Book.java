package com.manager.library.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Book")
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String titulo;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private String autor;

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Column(unique = true)
    private String isbn;

    @Past(message = "The publication date cannot be a future date")
    @NotNull(message = "Publication date is required")
    private LocalDate dataPublicacao;

    @NotBlank
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String categoria;
}
