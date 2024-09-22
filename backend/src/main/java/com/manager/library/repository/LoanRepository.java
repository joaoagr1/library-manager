package com.manager.library.repository;

import com.manager.library.domain.Loan;
import com.manager.library.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findAllByUserId(UUID userId);

    boolean existsByBookIdAndStatus(UUID bookId, LoanStatus loanStatus);

    List<Loan> findAllByBookId(UUID id);
}
