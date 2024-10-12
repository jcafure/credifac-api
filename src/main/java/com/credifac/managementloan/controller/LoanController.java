package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.service.LoanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Controller
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public String getFormCreateLoan(Model model) {
        model.addAttribute("loanRequestDTO", new LoanRequestDTO());
        return "/loans/create";
    }

    @GetMapping("/list")
    public String getFormListLoan(Model model) {
        var loans = loanService.findAll();
        model.addAttribute("loans", loans);
        return "/loans/list";
    }

    @GetMapping("/update/{id}")
    public String getFormUpdateLoan(Model model, @PathVariable Long id) {
        // TODO implementar
        return "";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        loanService.delete(id);
        return "redirect:/loans/list";
    }


    @PostMapping("/create")
    public String createLoan(@Valid LoanRequestDTO loanRequestDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/loans/create";
        }
        loanService.createLoan(loanRequestDTO);
        
        return "redirect:/loans/list";
    }

    private String formatCurrency(BigDecimal value) {
        Locale brazilLocale = new Locale("pt", "br");
        NumberFormat currencyFormater = NumberFormat.getCurrencyInstance(brazilLocale);

        return currencyFormater.format(value);
    }
}
