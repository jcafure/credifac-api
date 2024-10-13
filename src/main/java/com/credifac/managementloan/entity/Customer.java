package com.credifac.managementloan.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{

    private String name;
    private String phoneNumber;
    private LocalDate registrationDate;
}
