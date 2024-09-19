package com.credifac.managementloan.service;


import com.credifac.managementloan.domain.LoanStatus;
import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.entity.Customer;
import com.credifac.managementloan.entity.Loan;
import com.credifac.managementloan.mapper.LoanMapper;
import com.credifac.managementloan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class LoanService {

    private final InstallmentService installmentService;
    private final LoanMapper mapper;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(InstallmentService installmentService,
                       LoanMapper mapper,
                       LoanRepository loanRepository) {
        this.installmentService = installmentService;
        this.mapper = mapper;
        this.loanRepository = loanRepository;
    }

    public LoanDTO createLoan(LoanRequestDTO loanRequestDTO){
        var loan = buildLoan(loanRequestDTO.dateLoan, loanRequestDTO.loanAmount);
        loan.setCustomer(buildCustomer(loanRequestDTO.personName, loanRequestDTO.phoneNumber));
        loan.setInstallments(installmentService.generateValue(loanRequestDTO.loanAmount, loan.getDateLoan()));

        return mapper.toDto(loanRepository.save(loan));
    }

    private Customer buildCustomer(String name, String phoneNumber){
        var customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);
        customer.setRegistrationDate(LocalDate.now());
        return customer;
    }

    private Loan buildLoan(LocalDate loanDate, BigDecimal loanAmount) {
        var loan = new Loan();
        loan.setTotalAmount(loanAmount);
        loan.setDateLoan(loanDate);
        loan.setInstallments(new ArrayList<>());
        loan.setLoanStatus(LoanStatus.ACTIVE);
        return loan;
    }
}
