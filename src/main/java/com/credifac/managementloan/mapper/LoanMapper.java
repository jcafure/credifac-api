package com.credifac.managementloan.mapper;

import com.credifac.managementloan.dto.*;
import com.credifac.managementloan.entity.Loan;

import com.credifac.managementloan.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public LoanUpdateDTO mapLoanToLoanUpdateDTO(Loan loan) {
        var dto = new LoanUpdateDTO();
        dto.setNameCustomer(loan.getCustomer().getName());
        dto.setPhoneNumber(StringUtils.formatPhoneNumber(loan.getCustomer().getPhoneNumber()));
        dto.setLoanDateFormated(StringUtils.formatDateToBrazilianFormat(loan.getDateLoan()));
        dto.setTotalAmountformated(StringUtils.formatCurrency(loan.getTotalAmount()));
        dto.setLoanStatus(loan.getLoanStatus().getNameStatus());
        dto.setInstallmentDTOList(getInstallmentUpdateDTOS(loan));

        return dto;
    }

    private static List<InstallmentUpdateDTO> getInstallmentUpdateDTOS(Loan loan) {
        return loan.getInstallments().stream()
                .map(installment -> InstallmentUpdateDTO
                        .builder()
                        .id(installment.getId())
                        .dueDateFormated(StringUtils.formatDateToBrazilianFormat(installment.getDueDate()))
                        .installmentNumber(installment.getInstallmentNumber())
                        .paymentStatus(installment.getPaymentStatus().getNamePaymentStatus())
                        .installmentAmountFormated(StringUtils.formatCurrency(installment.getInstallmentAmount()))
                        .build())
                .collect(Collectors.toList());
    }
}