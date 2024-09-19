package com.credifac.managementloan.controller;

import com.credifac.managementloan.entity.Customer;
import com.credifac.managementloan.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new Customer());
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(Customer user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        customerRepository.save(user);
        return "redirect:/index";
    }

}
