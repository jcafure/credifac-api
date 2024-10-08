package com.credifac.managementloan.entity;

import com.credifac.managementloan.domain.LoanStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "loan")

public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Installment> installments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private BigDecimal totalAmount;

    private LocalDate dateLoan;
}
