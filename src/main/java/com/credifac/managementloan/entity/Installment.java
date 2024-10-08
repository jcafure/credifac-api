package com.credifac.managementloan.entity;

import com.credifac.managementloan.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "installment")
public class Installment extends BaseEntity {

    private int installmentNumber;

    private BigDecimal installmentAmount;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
