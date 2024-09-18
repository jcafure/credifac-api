package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create-loan")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        LoanDTO loan = loanService.createLoan(loanRequestDTO);
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }
}
