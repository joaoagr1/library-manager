package com.manager.library.model.dtos;

public record ErrorReponseDTO(
        String error,
        int status
) {
}
