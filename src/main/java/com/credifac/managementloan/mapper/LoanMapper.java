package com.credifac.managementloan.mapper;

import com.credifac.managementloan.dto.CustomerDTO;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.entity.Loan;

import com.credifac.managementloan.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LoanMapper {

    private final InstallmentMapper installmentMapper;

    public LoanDTO toDto(Loan loan) {
        var loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setLoanStatus(loan.getLoanStatus().getNameStatus());
        loanDTO.setTotalAmount(loan.getTotalAmount());
        loanDTO.setTotalAmountformated(StringUtils.formatCurrency(loan.getTotalAmount()));
        loanDTO.setLoanDate(loan.getDateLoan());
        loanDTO.setLoanDateFormated(StringUtils.formatDateToBrazilianFormat(loan.getDateLoan()));
        loanDTO.setCustomer(new CustomerDTO());
        loanDTO.getCustomer().setName(loan.getCustomer().getName());
        loanDTO.getCustomer().setPhoneNumber(StringUtils.formatPhoneNumber(loan.getCustomer().getPhoneNumber()));
        loanDTO.getInstallmentList().addAll(installmentMapper.toDto(loan.getInstallments()));
        return loanDTO;
    }
}
