package com.credifac.managementloan.mapper;

import com.credifac.managementloan.dto.CustomerDTO;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    private final InstallmentMapper installmentMapper;

    @Autowired
    public LoanMapper(InstallmentMapper installmentMapper) {
        this.installmentMapper = installmentMapper;
    }

    public LoanDTO toDto(Loan loan) {
        var loanDTO = new LoanDTO();
        loanDTO.id = loan.getId();
        loanDTO.loanStatus = loan.getLoanStatus().name();
        loanDTO.totalAmount = loan.getTotalAmount();
        loanDTO.loanDate = loan.getDateLoan();
        loanDTO.customerDTO = new CustomerDTO();
        loanDTO.customerDTO.name = loan.getCustomer().getName();
        loanDTO.installmentDTOList.addAll(installmentMapper.toDto(loan.getInstallments()));
        return loanDTO;
    }
}
