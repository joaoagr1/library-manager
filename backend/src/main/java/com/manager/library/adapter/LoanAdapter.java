package com.manager.library.adapter;

import com.manager.library.domain.Book;
import com.manager.library.domain.Loan;
import com.manager.library.domain.Users;
import com.manager.library.dtos.LoanRequestDTO;
import com.manager.library.exceptions.EntityNotFoundException;
import com.manager.library.repository.BookRepository;
import com.manager.library.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanAdapter {

    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public LoanAdapter(BookRepository bookRepository, UsersRepository usersRepository) {

        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;

    }

    public Loan toEntity(LoanRequestDTO dto) {

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + dto.bookId()));

        Users user = usersRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.userId()));

        return Loan.builder()
                .returnDate(dto.returnDate())
                .book(book)
                .user(user)
                .build();

    }


}
