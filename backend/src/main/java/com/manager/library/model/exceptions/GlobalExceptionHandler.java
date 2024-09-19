package com.manager.library.model.exceptions;

import com.manager.library.model.dtos.ErrorReponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


@ControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(ConstraintViolationException.class)
        @ResponseBody
        public ResponseEntity<ErrorReponseDTO> handleConstraintViolationException(ConstraintViolationException ex) {

            Optional<ConstraintViolation<?>> firstViolation = ex.getConstraintViolations().stream().findFirst();


            String detailedErrorMessage = firstViolation
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .orElse("Unknown validation error");


            ErrorReponseDTO errorDTO = new ErrorReponseDTO(
                    detailedErrorMessage,
                    HttpStatus.BAD_REQUEST.value()
            );

            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
        }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorReponseDTO> handleIllegalArgumentsException(IllegalArgumentException ex) {

            ErrorReponseDTO errorDTO = new ErrorReponseDTO(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST.value()
            );

            return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorReponseDTO> handleUserNotFoundException(EntityNotFoundException ex) {

        ErrorReponseDTO errorDTO = new ErrorReponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);

    }}