package com.manager.library.service;

import com.manager.library.adapter.LoanAdapter;
import com.manager.library.domain.Loan;
import com.manager.library.dtos.LoanRequestDTO;
import com.manager.library.enums.LoanStatus;
import com.manager.library.exceptions.BookNotAvailableException;
import com.manager.library.exceptions.EntityNotFoundException;
import com.manager.library.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanAdapter loanAdapter;

    public Loan createLoan(LoanRequestDTO loanRequestDTO) {

        if (hasActiveLoanForBook(loanRequestDTO.bookId())) {
            throw new BookNotAvailableException("There is already an active loan for this book");
        }

        Loan loan = loanAdapter.toEntity(loanRequestDTO);
        return loanRepository.save(loan);

    }

    public List<Loan> getAllLoans() {

        return loanRepository.findAll();

    }

    public Optional<Loan> getLoanById(UUID id) {

        loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        return loanRepository.findById(id);

    }

    @Transactional
    public Loan updateLoan(UUID id, LoanRequestDTO loanRequestDTO) {

        Loan existingLoan = loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        Loan loanToUpdate = loanAdapter.toEntity(loanRequestDTO);

        loanToUpdate.setId(existingLoan.getId());

        return loanRepository.save(loanToUpdate);

    }

    public void deleteLoan(UUID id) {

        loanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Loan not found"));
        loanRepository.deleteById(id);

    }

    public boolean hasActiveLoanForBook(UUID bookId) {

        return loanRepository.existsByBookIdAndStatus(bookId, LoanStatus.ACTIVE);

    }


}
