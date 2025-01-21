package com.credifac.managementloan.service;

import com.credifac.managementloan.domain.LoanStatus;
import com.credifac.managementloan.domain.PaymentStatus;
import com.credifac.managementloan.dto.*;
import com.credifac.managementloan.entity.Customer;
import com.credifac.managementloan.entity.Installment;
import com.credifac.managementloan.entity.Loan;
import com.credifac.managementloan.mapper.LoanMapper;
import com.credifac.managementloan.repository.LoanRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public LoanUpdateDTO findById(Long id) {
        return loanRepository.findById(id)
                .map(mapper::mapLoanToLoanUpdateDTO)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo com ID " + id + " não encontrado."));
    }

    public void delete(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        loanOptional.ifPresent(loan -> loanRepository.delete(loan));
    }

    public Loan updateLoan(LoanUpdateDTO loanUpdateDTO) {
        Optional<Loan> loan = loanRepository.findById(loanUpdateDTO.getIdLoan());
        if (loan.isPresent()){
            var loanEntity = loan.get();
            loanEntity.getCustomer().setPhoneNumber(loanUpdateDTO.getPhoneNumber());
            if (loanUpdateDTO.getInstallmentDTOList() != null){
                for (InstallmentUpdateDTO installmentDTO : loanUpdateDTO.getInstallmentDTOList()) {
                    loanEntity.getInstallments().stream()
                            .filter(installment -> installment.getId().equals(installmentDTO.getId()))
                            .forEach(installment -> {
                                installment.setPaymentStatus(PaymentStatus.fromDisplayName(installmentDTO.getPaymentStatus()));
                                installment.setLoan(loanEntity);
                            });
                }
            }
            return loanRepository.save(loanEntity);
        }else {
            throw new EntityNotFoundException("Empréstimo não encontrado com ID: " + loanUpdateDTO.getIdLoan());
        }
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

    public List<LoanDTO> findByFilters(String search) {
        return loanRepository.findByFilters(search)
                .stream()
                .map(loan -> mapper.toDto(loan))
                .collect(Collectors.toList());
    }
}
