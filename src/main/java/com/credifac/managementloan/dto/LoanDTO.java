package com.credifac.managementloan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    private Long id;
    private String loanStatus;
    private LocalDate loanDate;
    private BigDecimal totalAmount;
    private List<InstallmentDTO> installmentList = new ArrayList<>();
    private CustomerDTO customer;
}
