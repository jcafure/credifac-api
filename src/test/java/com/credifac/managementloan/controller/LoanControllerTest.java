// package com.credifac.managementloan.controller;

// import com.credifac.managementloan.dto.LoanDTO;
// import com.credifac.managementloan.dto.LoanRequestDTO;
// import com.credifac.managementloan.service.LoanService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.math.BigDecimal;
// import java.time.LocalDate;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// @WebMvcTest(LoanController.class)
// class LoanControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private LoanService loanService;

//     @InjectMocks
//     private LoanController loanController;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void createLoan()   throws Exception {
//         final var loanRequest = new LoanRequestDTO();
//         loanRequest.setDateLoan(LocalDate.now());
//         loanRequest.setLoanAmount(BigDecimal.valueOf(5000));
//         loanRequest.setPersonName("julinho da van");
//         loanRequest.setPhoneNumber("67852145698");

//         final var loanDTO = new LoanDTO();
//         loanDTO.setTotalAmount(loanRequest.getLoanAmount());
//         loanDTO.setLoanDate(loanRequest.getDateLoan());

//         when(loanService.createLoan(any(LoanRequestDTO.class))).thenReturn(loanDTO);
//         mockMvc.perform(post("/loans/create")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(loanRequest)))
//                 .andExpect(status().is2xxSuccessful());

//         //verify(loanService, times(1)).createLoan(any(LoanRequestDTO.class));
//     }
// }