package com.credifac.managementloan.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentDTO {

    public int installmentNumber;
    public BigDecimal installmentAmount;
    public LocalDate dueDate;
    public String paymentStatus;
}
