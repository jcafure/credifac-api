package com.credifac.managementloan.service;

import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.entity.Installment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InstallmentService {

    public List<Installment> generateValue(BigDecimal loanAmount, LocalDate dateLoan){
        BigDecimal installmentAmount = loanAmount.divide(BigDecimal.valueOf(3), RoundingMode.HALF_UP);
        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> createInstallment(i, installmentAmount, dateLoan))
                .collect(Collectors.toList());
    }

    private Installment createInstallment(int installmentNumber, BigDecimal installmentAmount, LocalDate loanDate) {
        var installment = new Installment();

        installment.setInstallmentNumber(installmentNumber);
        installment.setInstallmentAmount(installmentAmount);

        LocalDate nextMonth = loanDate.plusMonths(installmentNumber);
        LocalDate dueDate = nextMonth.withDayOfMonth(1).plusMonths(1).minusDays(1);

        installment.setDueDate(dueDate);
        installment.setPaymentStatus(PaymentStatus.OPEN);
        return installment;
    }
}
