package com.example.springbackend.controller;

import com.example.springbackend.entity.BankingAccount;
import com.example.springbackend.entity.BankingCard;
import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.BankingAccountService;
import com.example.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SignUpController {

    private UserService userService;
    private BankingAccountService bankingAccountService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public SignUpController(UserService userService, BankingAccountService bankingAccountService) {
        this.userService = userService;
        this.bankingAccountService = bankingAccountService;
    }

    @GetMapping("/showSignUpPage")
    public String showSignUpPage(Model model) {

        UserInfo userInfo = new UserInfo();
        model.addAttribute("user", userInfo);
        return "signup-form";
    }

}
