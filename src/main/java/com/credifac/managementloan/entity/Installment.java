package com.credifac.managementloan.entity;

import com.credifac.managementloan.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @GeneratedValue
    private Long id;

    private int installmentNumber;

    private BigDecimal installmentAmount;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
