package com.credifac.managementloan.mapper;

import com.credifac.managementloan.dto.CustomerDTO;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.entity.Loan;

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
        loanDTO.setTotalAmountformated(formatCurrency(loan.getTotalAmount()));
        loanDTO.setLoanDate(loan.getDateLoan());
        loanDTO.setLoanDateFormated(formatDateToBrazilianFormat(loan.getDateLoan()));
        loanDTO.setCustomer(new CustomerDTO());
        loanDTO.getCustomer().setName(loan.getCustomer().getName());
        loanDTO.getCustomer().setPhoneNumber(formatPhoneNumber(loan.getCustomer().getPhoneNumber()));
        loanDTO.getInstallmentList().addAll(installmentMapper.toDto(loan.getInstallments()));
        return loanDTO;
    }

    private String formatCurrency(BigDecimal value) {
        var brazilLocale = new Locale("pt", "br");
        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(brazilLocale);

        return currencyFormater.format(value);
    }

    private static String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\D", "");

        if (phoneNumber.length() == 11) {
            return "(" + phoneNumber.substring(0, 2) + ") "
                    + phoneNumber.substring(2, 7) + "-"
                    + phoneNumber.substring(7);
        } else if (phoneNumber.length() == 10) {
            return "(" + phoneNumber.substring(0, 2) + ") "
                    + phoneNumber.substring(2, 6) + "-"
                    + phoneNumber.substring(6);
        } else {
            return phoneNumber;
        }
    }

    private static String formatDateToBrazilianFormat(LocalDate date) {
        var brazilianFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(brazilianFormatter);
    }
}
