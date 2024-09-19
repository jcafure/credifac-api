package com.credifac.managementloan.mapper;

import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.dto.InstallmentDTO;
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

class InstallmentMapperTest {

    @InjectMocks
    private InstallmentMapper installmentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDto() {
        Installment installment = new Installment();
        installment.setInstallmentAmount(BigDecimal.valueOf(1000));
        installment.setInstallmentNumber(3);
        installment.setPaymentStatus(PaymentStatus.OPEN);
        installment.setDueDate(LocalDate.now());

        List<InstallmentDTO> dto = installmentMapper.toDto(List.of(installment));

        Assertions.assertThat(dto.getFirst().paymentStatus).isEqualTo(PaymentStatus.OPEN.name());
        Assertions.assertThat(dto.getFirst().installmentAmount).isEqualTo(installment.getInstallmentAmount());
        Assertions.assertThat(dto.getFirst().dueDate).isEqualTo(installment.getDueDate());
        Assertions.assertThat(dto.getFirst().installmentNumber).isEqualTo(installment.getInstallmentNumber());

    }
}