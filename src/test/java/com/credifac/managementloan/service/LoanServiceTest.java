 package com.credifac.managementloan.service;

 import com.credifac.managementloan.domain.PaymentStatus;
 import com.credifac.managementloan.dto.InstallmentUpdateDTO;
 import com.credifac.managementloan.dto.LoanDTO;
 import com.credifac.managementloan.dto.LoanRequestDTO;
 import com.credifac.managementloan.dto.LoanUpdateDTO;
 import com.credifac.managementloan.entity.Customer;
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
 import java.util.Optional;

 import static org.assertj.core.api.Assertions.assertThat;
 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.ArgumentMatchers.any;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;

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

         when(installmentService.generateValue(loanRequestDTO.getLoanAmount(), loanRequestDTO.getDateLoan())).
                 thenReturn(List.of(installment01, installment02, installment03));
         when(loanRepository.save(any(Loan.class))).thenReturn(loan);
         when(mapper.toDto(any(Loan.class))).thenReturn(new LoanDTO());

         LoanDTO result = service.createLoan(loanRequestDTO);

         assertNotNull(result);
         verify(installmentService).generateValue(loanRequestDTO.getLoanAmount(), loanRequestDTO.getDateLoan());
         verify(loanRepository).save(any(Loan.class));
         verify(mapper).toDto(any(Loan.class));
     }

     @Test
     void testUpdateLoan() {
         final var loan = new Loan();
         final var customer = new Customer();
         final var installment1 = new Installment();
         final var installmentUpdateDTO = new InstallmentUpdateDTO();
         loan.setId(1L);
         loan.setCustomer(customer);
         customer.setPhoneNumber("123456789");
         installment1.setId(1L);
         installment1.setPaymentStatus(PaymentStatus.OPEN);
         loan.setInstallments(List.of(installment1));
         LoanUpdateDTO loanUpdateDTO = new LoanUpdateDTO();
         loanUpdateDTO.setIdLoan(1L);
         loanUpdateDTO.setPhoneNumber("987654321");
         installmentUpdateDTO.setId(1L);
         installmentUpdateDTO.setPaymentStatus(PaymentStatus.PAID.getNamePaymentStatus());
         loanUpdateDTO.setInstallmentDTOList(List.of(installmentUpdateDTO));

         when(loanRepository.findById(loanUpdateDTO.getIdLoan())).thenReturn(Optional.of(loan));
         when(loanRepository.save(any(Loan.class))).thenReturn(loan);
         Loan updatedLoan = service.updateLoan(loanUpdateDTO);

         assertThat(updatedLoan.getCustomer().getPhoneNumber()).isEqualTo("987654321");
         assertThat(updatedLoan.getInstallments().get(0).getPaymentStatus()).isEqualTo(PaymentStatus.PAID);

         verify(loanRepository).save(loan);
     }
 }