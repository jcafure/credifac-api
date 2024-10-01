package com.credifac.managementloan.mapper;

import com.credifac.managementloan.domain.LoanStatus;
import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.dto.InstallmentDTO;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.entity.Customer;
import com.credifac.managementloan.entity.Installment;
import com.credifac.managementloan.entity.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class LoanMapperTest {

    @InjectMocks
    private LoanMapper mapper;

    @Mock
    private InstallmentMapper installmentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToDto() {
        final var loanEntity = new Loan();
        final var personEntity = new Customer();
        final var installment = new Installment();
        final var installmentDto = new InstallmentDTO();

        installmentDto.dueDate = LocalDate.now();
        installmentDto.installmentAmount = BigDecimal.valueOf(1000);
        installmentDto.installmentNumber = 3;
        installmentDto.paymentStatus = PaymentStatus.OPEN.name();

        installment.setDueDate(LocalDate.now());
        installment.setInstallmentAmount(BigDecimal.valueOf(333));
        installment.setId(1L);
        installment.setInstallmentNumber(3);
        installment.setPaymentStatus(PaymentStatus.OPEN);

        personEntity.setName("charlinhos");
        loanEntity.setId(1L);
        loanEntity.setDateLoan(LocalDate.now());
        loanEntity.setCustomer(personEntity);
        loanEntity.setTotalAmount(BigDecimal.valueOf(1000));
        loanEntity.setLoanStatus(LoanStatus.ACTIVE);

        List<Installment> installments = new ArrayList<>();
        installments.add(installment);
        loanEntity.setInstallments(installments);

        Mockito.when(installmentMapper.toDto(installments)).thenReturn(List.of(installmentDto));

        LoanDTO loanDTO = mapper.toDto(loanEntity);
        Assertions.assertThat(loanDTO.getId()).isEqualTo(loanEntity.getId());
        Assertions.assertThat(loanDTO.getLoanStatus()).isEqualTo(loanEntity.getLoanStatus().name());
        Assertions.assertThat(loanDTO.getCustomer().getName()).isEqualTo(loanEntity.getCustomer().getName());
        Assertions.assertThat(loanDTO.getTotalAmount()).isEqualTo(loanEntity.getTotalAmount());
        Assertions.assertThat(loanDTO.getInstallmentList()).isNotEmpty();

    }
}