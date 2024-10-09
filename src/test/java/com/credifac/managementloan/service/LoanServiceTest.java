 package com.credifac.managementloan.service;

 import com.credifac.managementloan.domain.PaymentStatus;
 import com.credifac.managementloan.dto.LoanDTO;
 import com.credifac.managementloan.dto.LoanRequestDTO;
 import com.credifac.managementloan.entity.Installment;
 import com.credifac.managementloan.entity.Loan;
 import com.credifac.managementloan.mapper.LoanMapper;
 import com.credifac.managementloan.repository.LoanRepository;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;

 import java.math.BigDecimal;
 import java.time.LocalDate;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.verify;

 class LoanServiceTest {

     @InjectMocks
     private LoanService service;
     @Mock
     private  InstallmentService installmentService;
     @Mock
     private  LoanMapper mapper;
     @Mock
     private  LoanRepository loanRepository;

     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
     }

     @Test
     void createLoan() {
         final var loanRequestDTO = new LoanRequestDTO();
         final var installment01 = new Installment();
         final var installment02 = new Installment();
         final var installment03 = new Installment();

         loanRequestDTO.setDateLoan(LocalDate.now());
         loanRequestDTO.setLoanAmount(BigDecimal.valueOf(5000));
         loanRequestDTO.setPersonName("Charlinhu");
         loanRequestDTO.setPhoneNumber("67982154787");

         installment01.setInstallmentNumber(1);
         installment01.setPaymentStatus(PaymentStatus.OPEN);
         installment01.setInstallmentAmount(BigDecimal.valueOf(1666));
         installment01.setDueDate(LocalDate.of(2024, 10, 19));
         installment01.setId(1L);

         installment02.setInstallmentNumber(2);
         installment02.setDueDate(LocalDate.of(2024, 11, 19));
         installment02.setInstallmentAmount(BigDecimal.valueOf(1666));
         installment02.setPaymentStatus(PaymentStatus.OPEN);
         installment02.setId(2L);

         installment03.setInstallmentNumber(3);
         installment03.setInstallmentAmount(BigDecimal.valueOf(1666));
         installment03.setDueDate(LocalDate.of(2024,11,19));
         installment03.setPaymentStatus(PaymentStatus.OPEN);
         installment03.setId(3L);

         final var loan = new Loan();
         loan.setDateLoan(loanRequestDTO.getDateLoan());
         loan.setTotalAmount(loanRequestDTO.getLoanAmount());
         loan.setInstallments(List.of(installment01, installment02, installment03));

         Mockito.when(installmentService.generateValue(loanRequestDTO.getLoanAmount(), loanRequestDTO.getDateLoan())).
                 thenReturn(List.of(installment01, installment02, installment03));
         Mockito.when(loanRepository.save(Mockito.any(Loan.class))).thenReturn(loan);
         Mockito.when(mapper.toDto(Mockito.any(Loan.class))).thenReturn(new LoanDTO());

         LoanDTO result = service.createLoan(loanRequestDTO);

         assertNotNull(result);
         verify(installmentService).generateValue(loanRequestDTO.getLoanAmount(), loanRequestDTO.getDateLoan());
         verify(loanRepository).save(Mockito.any(Loan.class));
         verify(mapper).toDto(Mockito.any(Loan.class));
     }
 }