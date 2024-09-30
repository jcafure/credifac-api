package com.credifac.managementloan.mapper;

import com.credifac.managementloan.dto.CustomerDTO;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.entity.Loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanMapper {

    private final InstallmentMapper installmentMapper;

    public LoanDTO toDto(Loan loan) {
        var loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setLoanStatus(loan.getLoanStatus().name());
        loanDTO.setTotalAmount(loan.getTotalAmount());
        loanDTO.setLoanDate(loan.getDateLoan());
        loanDTO.setCustomer(new CustomerDTO());
        loanDTO.getCustomer().setName(loan.getCustomer().getName());
        loanDTO.getCustomer().setPhoneNumber(loan.getCustomer().getPhoneNumber());
        loanDTO.getInstallmentList().addAll(installmentMapper.toDto(loan.getInstallments()));
        return loanDTO;
    }
}
