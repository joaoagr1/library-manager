package com.manager.library.model.service;

import com.manager.library.model.adapter.LoanAdapter;
import com.manager.library.model.domain.Loan;
import com.manager.library.model.dtos.LoanRequestDTO;
import com.manager.library.model.repository.LoanRepository;
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

    public Loan createLoan(LoanRequestDTO loanRequestDTO) {

        Loan loan = LoanAdapter.toEntity(loanRequestDTO);
        return loanRepository.save(loan);

    }

    public List<Loan> getAllLoans() {

        return loanRepository.findAll();

    }

    public Optional<Loan> getLoanById(UUID id) {
        return loanRepository.findById(id);
    }

    @Transactional
    public Loan updateLoan(UUID id, LoanRequestDTO loanRequestDTO) {

        Optional<Loan> existingLoan = loanRepository.findById(id);

        Loan loanToUpdate = LoanAdapter.toEntity(loanRequestDTO);

        loanToUpdate.setId(existingLoan.get().getId());

        return loanRepository.save(loanToUpdate);

    }

    public void deleteLoan(UUID id) {

        loanRepository.deleteById(id);

    }


}
