package com.credifac.managementloan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {

    public String personName;
    public String phoneNumber;
    public LocalDate dateLoan;
    public BigDecimal loanAmount;
}
