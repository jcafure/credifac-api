package com.credifac.managementloan.service;

import com.credifac.managementloan.domain.LoanStatus;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.entity.Customer;
import com.credifac.managementloan.entity.Installment;
import com.credifac.managementloan.entity.Loan;
import com.credifac.managementloan.mapper.LoanMapper;
import com.credifac.managementloan.repository.LoanRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final InstallmentService installmentService;
    private final LoanMapper mapper;
    private final LoanRepository loanRepository;

    public LoanDTO createLoan(LoanRequestDTO loanRequestDTO) {
        var loan = buildLoan(loanRequestDTO.getDateLoan(), loanRequestDTO.getLoanAmount());
        loan.setCustomer(buildCustomer(loanRequestDTO.getPersonName(), loanRequestDTO.getPhoneNumber()));
        List<Installment> installments = installmentService.generateValue(loanRequestDTO.getLoanAmount(), loan.getDateLoan());
        installments.forEach(installment -> addInstallment(loan, installment));
        return mapper.toDto(loanRepository.save(loan));
    }

    public List<LoanDTO> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(loan -> mapper.toDto(loan))
                .collect(toList());
    }

    public void delete(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        loanOptional.ifPresent(loan -> loanRepository.delete(loan));
    }

    private static Customer buildCustomer(String name, String phoneNumber) {
        var customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setRegistrationDate(LocalDate.now());
        return customer;
    }

    private static Loan buildLoan(LocalDate loanDate, BigDecimal loanAmount) {
        var loan = new Loan();
        loan.setTotalAmount(loanAmount);
        loan.setDateLoan(loanDate);
        loan.setInstallments(new ArrayList<>());
        loan.setLoanStatus(LoanStatus.ACTIVE);
        return loan;
    }

    private static void addInstallment(Loan loan, Installment installment) {
        loan.getInstallments().add(installment);
        installment.setLoan(loan);
    }
}
