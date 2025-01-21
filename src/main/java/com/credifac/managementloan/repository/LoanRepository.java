package com.credifac.managementloan.repository;

import com.credifac.managementloan.entity.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l FROM Loan l WHERE " +
         "(:search IS NULL OR LOWER(l.customer.name) LIKE %:search%) OR " +
         "(:search IS NULL OR l.customer.phoneNumber LIKE %:search%) OR " +
         "(:search IS NULL OR TO_CHAR(l.dateLoan, 'DD/MM/YYYY') LIKE %:search%) OR " +
         "(:search IS NULL OR LOWER(CAST(l.loanStatus AS string)) LIKE %:search%) OR " +
         "(:search IS NULL OR CAST(l.totalAmount AS text) LIKE %:search%)")
    List<Loan> findByFilters(@Param("search") String search);
}
