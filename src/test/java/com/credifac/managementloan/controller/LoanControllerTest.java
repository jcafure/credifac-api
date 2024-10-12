package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnLoanCreateForm() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loans/create"))
                .andExpect(model().attributeExists("loanRequestDTO"));
    }

    @Test
    void shouldCreateLoanAndRedirectToList() throws Exception {
        LoanRequestDTO loanRequest = new LoanRequestDTO();
        loanRequest.setDateLoan(LocalDate.now());
        loanRequest.setLoanAmount(BigDecimal.valueOf(5000));
        loanRequest.setPersonName("Julinho da Van");
        loanRequest.setPhoneNumber("67852145698");

        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setTotalAmount(loanRequest.getLoanAmount());
        loanDTO.setLoanDate(loanRequest.getDateLoan());
        when(loanService.createLoan(any(LoanRequestDTO.class))).thenReturn(loanDTO);

        mockMvc.perform(post("/loans/create")
                        .param("personName", loanRequest.getPersonName())
                        .param("phoneNumber", loanRequest.getPhoneNumber())
                        .param("loanAmount", loanRequest.getLoanAmount().toString())
                        .param("dateLoan", loanRequest.getDateLoan().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans/list"));

        verify(loanService, times(1)).createLoan(any(LoanRequestDTO.class));
    }



    @Test
    void shouldListLoans() throws Exception {
        when(loanService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/loans/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loans/list"))
                .andExpect(model().attributeExists("loans"));
    }

    @Test
    void shouldDeleteLoanAndRedirectToList() throws Exception {
        doNothing().when(loanService).delete(1L);

        mockMvc.perform(get("/loans/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans/list"));

        verify(loanService, times(1)).delete(1L);
    }
}
