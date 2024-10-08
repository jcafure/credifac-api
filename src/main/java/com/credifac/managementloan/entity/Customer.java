package com.credifac.managementloan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity{

    private String name;
    private String phoneNumber;
    private LocalDate registrationDate;
}
