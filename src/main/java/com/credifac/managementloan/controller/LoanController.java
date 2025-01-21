package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.dto.LoanUpdateDTO;
import com.credifac.managementloan.entity.Loan;
import com.credifac.managementloan.service.LoanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getFormListLoan(Model model, @RequestParam(required = false) String search) {
        List<LoanDTO> loans = loanService.findByFilters(search);
        model.addAttribute("loans", loans);
        model.addAttribute("search", search);
        return "/loans/list";
    }

    @GetMapping("/update/{id}")
    public String getFormUpdateLoan(Model model, @PathVariable Long id) {
        LoanUpdateDTO dto = loanService.findById(id);
        model.addAttribute("loanUpdateDTO", dto);
        return "/loans/edit";
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

    @PostMapping("/update")
    public String updateLoan(@ModelAttribute LoanUpdateDTO loanUpdateDTO){
        loanService.updateLoan(loanUpdateDTO);
        return "redirect:/loans/list";
    }
}
