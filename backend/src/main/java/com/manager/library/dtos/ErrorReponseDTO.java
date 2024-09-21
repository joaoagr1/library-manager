package com.manager.library.dtos;

public record ErrorReponseDTO(
        String error,
        int status
) {
}
