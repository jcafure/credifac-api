package com.credifac.managementloan.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentDTO {

    public int installmentNumber;
    public BigDecimal installmentAmount;
    public LocalDate dueDate;
    public String paymentStatus;
}
