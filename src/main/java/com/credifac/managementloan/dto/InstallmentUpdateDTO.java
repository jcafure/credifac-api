package com.credifac.managementloan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
