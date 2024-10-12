package com.credifac.managementloan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoanStatus {
    ACTIVE("Ativo"),
    PAID_OFF("Pago"),
    DELINQUENT("Inadimplente"),
    CANCELLED("Cancelado"),
    PENDING("Pendente");

    private final String nameStatus;
}
