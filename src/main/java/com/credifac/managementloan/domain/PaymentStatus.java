package com.credifac.managementloan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PAID("Pago"),
    DELINQUENT("Inadimplente"),
    OPEN("Aberta"),
    PARTIALLY_PAID("Parcialmente Pago");

    private final String namePaymentStatus;
}
