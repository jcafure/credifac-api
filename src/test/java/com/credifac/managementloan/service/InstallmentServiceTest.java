package com.credifac.managementloan.service;

import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.entity.Installment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstallmentServiceTest {

    @InjectMocks
    private InstallmentService installmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateValue() {
        BigDecimal loanAmount = new BigDecimal("3000.00");
        LocalDate dateLoan = LocalDate.of(2024, 9, 19);

        List<Installment> installments = installmentService.generateValue(loanAmount, dateLoan);
        BigDecimal expectedInstallmentAmount = new BigDecimal("1000.00");

        Assertions.assertThat(installments).hasSize(3);

        Assertions.assertThat(installments.get(0).getInstallmentAmount()).isEqualTo(expectedInstallmentAmount);
        Assertions.assertThat(installments.get(1).getInstallmentAmount()).isEqualTo(expectedInstallmentAmount);
        Assertions.assertThat(installments.get(2).getInstallmentAmount()).isEqualTo(expectedInstallmentAmount);

        assertEquals(LocalDate.of(2024, 10, 31), installments.get(0).getDueDate());
        assertEquals(LocalDate.of(2024, 11, 30), installments.get(1).getDueDate());
        assertEquals(LocalDate.of(2024, 12, 31), installments.get(2).getDueDate());

        assertEquals(PaymentStatus.OPEN, installments.get(0).getPaymentStatus());
        assertEquals(PaymentStatus.OPEN, installments.get(1).getPaymentStatus());
        assertEquals(PaymentStatus.OPEN, installments.get(2).getPaymentStatus());
    }
}