package com.credifac.managementloan.controller;

import com.credifac.managementloan.dto.LoanDTO;
import com.credifac.managementloan.dto.LoanRequestDTO;
import com.credifac.managementloan.dto.LoanUpdateDTO;
import com.credifac.managementloan.entity.Loan;
import com.credifac.managementloan.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

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

    private LoanController loanController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShouldReturnLoanCreateForm() throws Exception {
        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loans/create"))
                .andExpect(model().attributeExists("loanRequestDTO"));
    }

    @Test
    void testShouldCreateLoanAndRedirectToList() throws Exception {
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
    void testShouldListLoans() throws Exception {
        when(loanService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/loans/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/loans/list"))
                .andExpect(model().attributeExists("loans"));
    }

    @Test
    void testShouldDeleteLoanAndRedirectToList() throws Exception {
        doNothing().when(loanService).delete(1L);

        mockMvc.perform(get("/loans/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans/list"));

        verify(loanService, times(1)).delete(1L);
    }

    @Test
    void testGetFormUpdateLoan_shouldReturnEditViewAndAddAttributesToModel() throws Exception {
        Long loanId = 1L;
        final var mockLoanUpdateDTO = new LoanUpdateDTO();
        mockLoanUpdateDTO.setNameCustomer("Test Customer");
        mockLoanUpdateDTO.setLoanDateFormated("2024-11-01");
        mockLoanUpdateDTO.setTotalAmountformated("1000.00");
        mockLoanUpdateDTO.setLoanStatus("OPEN");

        when(loanService.findById(loanId)).thenReturn(mockLoanUpdateDTO);

        mockMvc.perform(get("/loans/update/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect(view().name("/loans/edit"))
                .andExpect(model().attribute("loanUpdateDTO", mockLoanUpdateDTO));
    }

    @Test
    void testUpdateLoan() throws Exception {
        final var mockLoanUpdateDTO = new LoanUpdateDTO();
        mockLoanUpdateDTO.setNameCustomer("Test Customer");
        mockLoanUpdateDTO.setLoanDateFormated("2024-11-01");
        mockLoanUpdateDTO.setTotalAmountformated("1000.00");
        mockLoanUpdateDTO.setLoanStatus("OPEN");

        Loan mockLoanEntity = new Loan();
        when(loanService.updateLoan(mockLoanUpdateDTO)).thenReturn(mockLoanEntity);  // Simula retorno de um Loan

        mockMvc.perform(post("/loans/update")
                        .flashAttr("loanUpdateDTO", mockLoanUpdateDTO)) //
                .andExpect(status().is3xxRedirection())  //
                .andExpect(redirectedUrl("/loans/list")); //

        verify(loanService).updateLoan(mockLoanUpdateDTO);
    }
}
