package com.credifac.managementloan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallmentUpdateDTO {

    private Long id;
    private int installmentNumber;
    private String installmentAmountFormated;
    private String dueDateFormated;
    private String paymentStatus;

}
