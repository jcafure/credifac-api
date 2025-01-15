package com.credifac.managementloan.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum PaymentStatus {
    PAID("Pago"),
    DELINQUENT("Inadimplente"),
    OPEN("Aberta"),
    PARTIALLY_PAID("Parcialmente Pago");

    private final String namePaymentStatus;

    PaymentStatus(String namePaymentStatus) {
        this.namePaymentStatus = namePaymentStatus;
    }

    public static List<String> getAllPaymentStatusNames() {
        return Arrays.stream(PaymentStatus.values())
                .map(PaymentStatus::getNamePaymentStatus)
                .collect(Collectors.toList());
    }

    public static PaymentStatus fromDisplayName(String displayName) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getNamePaymentStatus().equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de pagamento inválido: " + displayName);
    }

}
