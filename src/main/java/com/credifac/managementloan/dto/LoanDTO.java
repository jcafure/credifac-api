package com.credifac.managementloan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDTO {
    public Long id;
    public String loanStatus;
    public LocalDate loanDate;
    public BigDecimal totalAmount;
    public List<InstallmentDTO> installmentDTOList = new ArrayList<>();
    public CustomerDTO customerDTO;

}
