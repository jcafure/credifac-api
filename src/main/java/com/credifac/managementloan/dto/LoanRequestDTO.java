package com.credifac.managementloan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {

    @NotEmpty(message = "Nome é um campo obrigatório!")
    private String personName;
    @NotEmpty(message = "Telefone é um campo obrigatório!")
    private String phoneNumber;
    @NotNull(message = "Data de Empréstimo é um campo obrigatório!")
    private LocalDate dateLoan;
    @NotNull(message = "Valor é um campo obrigatório!") 
    @Positive(message = "Valor deve ser maior que zero!")
    private BigDecimal loanAmount;
}
