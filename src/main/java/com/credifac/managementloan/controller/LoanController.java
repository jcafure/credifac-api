package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.service.LoanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/create")
    public String createLoan(@Valid LoanRequestDTO loanRequestDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/loans/create";
        }
        loanService.createLoan(loanRequestDTO);
        
        return "redirect:/loans/list";
    }

    @GetMapping
    public String getFormLoan(Model model) {
        model.addAttribute("loanRequestDTO", new LoanRequestDTO());
        return "/loans/create";
    }

    @GetMapping("/list")
    public String getFormListLoan(Model model) {
        var loans = loanService.findAll();
        model.addAttribute("loans", loans);
        return "/loans/list";
    }
}
