package com.manager.library.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRequestDTO(

        @NotBlank(message = "Name is required")
        @Length(min = 2, max = 50,message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        @Column(unique = true)
        String email,

        @NotBlank(message = "Phone is required")
        @Length(min = 10, max = 15)
        String phone

) {
}
