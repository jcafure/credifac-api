package com.credifac.managementloan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanUpdateDTO {

    private Long idLoan;
    private String nameCustomer;
    private String phoneNumber;
    private String loanDateFormated;
    private String totalAmountformated;
    private String loanStatus;
    private List<InstallmentUpdateDTO> installmentDTOList = new ArrayList<>();
    private List<String> statusInstallmentPayment = new ArrayList<>();
}
