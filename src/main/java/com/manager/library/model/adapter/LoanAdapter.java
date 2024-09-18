package com.manager.library.model.adapter;

import com.manager.library.model.domain.Book;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.LoanRequestDTO;
import com.manager.library.model.exceptions.EntityNotFoundException;
import com.manager.library.model.repository.BookRepository;
import com.manager.library.model.repository.UsersRepository;
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

        if (dto == null) {
            return null;
        }


        Loan loan = new Loan();
        loan.setReturnDate(dto.returnDate());


        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + dto.bookId()));
        Users user = usersRepository.findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.userId()));


        loan.setBook(book);
        loan.setUser(user);

        return loan;
    }
}
