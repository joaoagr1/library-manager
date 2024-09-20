package com.manager.library.model.repository;

import com.manager.library.model.domain.Loan;
import com.manager.library.model.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findAllByUserId(UUID userId);

    boolean existsByBookIdAndStatus(UUID bookId, LoanStatus loanStatus);
}
