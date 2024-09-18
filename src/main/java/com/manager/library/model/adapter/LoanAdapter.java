package com.manager.library.model.adapter;

import com.manager.library.model.domain.Loan;
import com.manager.library.model.dtos.LoanRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class LoanAdapter {

    public static Loan toEntity(LoanRequestDTO dto) {

        if (dto == null) {
            return null;
        }
        Loan loan = new Loan();
        loan.setLoanDate(dto.loanDate());
        loan.setReturnDate(dto.returnDate());

        return loan;
    }

}
