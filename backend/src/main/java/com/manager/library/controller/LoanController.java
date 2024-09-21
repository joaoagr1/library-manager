package com.manager.library.controller;

import com.manager.library.dtos.LoanRequestDTO;
import com.manager.library.domain.Loan;
import com.manager.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/library/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {

        Loan newLoan = loanService.createLoan(loanRequestDTO);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {

        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable UUID id) {

        Optional<Loan> loan = loanService.getLoanById(id);
        return ResponseEntity.of(loan);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable UUID id, @RequestBody LoanRequestDTO loanRequestDTO) {

        Loan updatedLoan = loanService.updateLoan(id, loanRequestDTO);
        return ResponseEntity.ok(updatedLoan);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID id) {

        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();

    }

}
