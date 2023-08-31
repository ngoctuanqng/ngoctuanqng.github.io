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
        return "signupform";
    }


    @GetMapping("/showTransferPage")
    public String showTransferPage(Model model) {
        return "transfer";
    }

    @GetMapping("/showInfoPage")
    public String showInfoPage(Model model) {
        return "infopage";
    }

    @PostMapping("/user/transferMoney")
    public String transferMoney(@RequestParam String nameOfAccountSend, @RequestParam int numberOfAccountReceive,
                                @RequestParam String nameOfAccountReceive,
                                @RequestParam float amount, @RequestParam String confirmPassword, Model model) {


        UserInfo userInfo = userService.findUserByUserName(nameOfAccountSend);
        String passwordSaved = userInfo.getPassword();
        boolean passwordMatch = passwordEncoder.matches(confirmPassword, passwordSaved.replace("{bcrypt}", ""));


        BankingAccount userInfoReceiveByNumber = bankingAccountService.findBankingAccountById(numberOfAccountReceive);
        if (userInfoReceiveByNumber == null) {
            String notFoundAccountReceiveByNumber = "Số tài khoản người nhận không đúng";
            model.addAttribute("notFoundAccountReceiveByNumber", notFoundAccountReceiveByNumber);
            return "transfer";
        }

        BankingAccount userInfoReceiveByName = bankingAccountService.findBankingAccountByUserName(nameOfAccountReceive);
        if (userInfoReceiveByName == null) {
            String notFoundAccountReceiveByName = "Tên người  nhận không chính xác";
            model.addAttribute("notFoundAccountReceiveByName", notFoundAccountReceiveByName);
            return "transfer";
        }

        if (!userInfoReceiveByNumber.getUser().getUserName().equals(nameOfAccountReceive)) {
            String notMatchBetweenNameandNumber = "Tên người nhận và số tài khoản người nhận không trùng khớp!";
            model.addAttribute("notMatchBetweenByNameandNumber", notMatchBetweenNameandNumber);
            return "transfer";
        }

        if (!passwordMatch) {
            String notCorrectPassword = "Mật khẩu không chính xác!";
            model.addAttribute("notCorrectPassword", notCorrectPassword);
            return "transfer";
        }

        userService.transferMoney(nameOfAccountSend, nameOfAccountReceive, amount);
        return "redirect:/";
    }

    @GetMapping("/findByUserLoggedIn")
    public String findByUserLoggedIn(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        UserInfo userInfo = userService.findUserByUserName(userName);

        BankingCard bankingCard = userInfo.getBankingCard();

        int cardCode = bankingCard.getCardCode();

        model.addAttribute("banking_card", cardCode);

        model.addAttribute("userInfo", userInfo);

        return "infopage";
    }


    @GetMapping("/showPaymentPage")
    public String showPaymentPage(Model model) {
        return "payment-page";
    }

    @PostMapping("/user/payTheBill")
    public String payTheBill(@RequestParam String nameOfPayingAccount, @RequestParam float amount,
                             @RequestParam String confirmPassword, Model model) {


        UserInfo userInfo = userService.findUserByUserName(nameOfPayingAccount);
        String passwordSaved = userInfo.getPassword();
        boolean passwordMatch = passwordEncoder.matches(confirmPassword, passwordSaved.replace("{bcrypt}", ""));


        if (!passwordMatch) {
            String notCorrectPassword = "Mật khẩu không chính xác!";
            model.addAttribute("notCorrectPassword", notCorrectPassword);
            return "payment-page";
        }

        userService.payTheBill(nameOfPayingAccount, amount);

        return "redirect:/";
    }

    @GetMapping("/showChangingPassword")
    public String showChangingPassword(Model model) {
        return "change-password";
    }


    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam String nameOfAccountChange, @RequestParam String newPassword,
                                 @RequestParam String confirmNewPassword , @RequestParam String confirmPassword, Model model) {


        UserInfo userInfo = userService.findUserByUserName(nameOfAccountChange);
        String passwordSaved = userInfo.getPassword();
        boolean passwordMatch = passwordEncoder.matches(confirmPassword, passwordSaved.replace("{bcrypt}",
                ""));
        String newPasswordBcrypt = passwordEncoder.encode(newPassword);


        if (!passwordMatch) {
            String notCorrectPassword = "Mật khẩu cũ không chính xác!";
            model.addAttribute("notCorrectPassword", notCorrectPassword);
            return "change-password";
        }

        if (!newPassword.contains(confirmNewPassword)) {
            String notMatchPassword = "Mật khẩu xác thực không trùng khớp!";
            model.addAttribute("notMatchPassword", notMatchPassword);
            return "change-password";
        }

        userInfo.setPassword("{bcrypt}" + newPasswordBcrypt);

        userService.updateUser(userInfo);

        return "redirect:/";
    }

    @GetMapping("/showTransactionHistory")
    public String showTransactionHistory(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String nameOfUser = authentication.getName();

        UserInfo userInfo = userService.findUserByUserName(nameOfUser);

        model.addAttribute("transactionHistory", userInfo.getTransactionHistory());

        return "transaction-history";
    }

//    @PostMapping("/user/TransactionHistory")
//    public String TransactionHistory(@RequestParam String nameOfAccountChange, Model model) {
//
//
//        UserInfo userInfo = userService.findUserByUserName(nameOfAccountChange);
//
//
//        if (!newPassword.contains(confirmNewPassword)) {
//            String notMatchPassword = "Mật khẩu xác thực không trùng khớp!";
//            model.addAttribute("notMatchPassword", notMatchPassword);
//            return "change-password";
//        }
//
//        userInfo.setPassword("{bcrypt}" + newPasswordBcrypt);
//
//        userService.updateUser(userInfo);
//
//        return "redirect:/";
//    }



    @GetMapping("/admin/listOfUsers")
    public String listOfUsers(Model model) {
        List<UserInfo> userInfo = userService.findAllUsers();
        model.addAttribute("users", userInfo);
        return "list-users";
    }

    @PostMapping("/user/showUpdateUserForm")
    public String showUpdateUserForm(@RequestParam("id") int id,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     @RequestParam("authority") String authority,
                                     @RequestParam("fullName") String fullName,
                                     @RequestParam("phoneNumber") int phoneNumber, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);
        model.addAttribute("authority", authority);
        model.addAttribute("fullName", fullName);
        model.addAttribute("phoneNumber", phoneNumber);
        return "update-user";
    }

    @PostMapping("/user/update")
    public String updateUser(
                             @RequestParam("id") int id,
                             @RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("authority") String authority,
                             @RequestParam("fullName") String fullName,
                             @RequestParam("phoneNumber") int phoneNumber, Model model) {
        UserInfo userInfo = userService.findUserById(id);

        String passwordEncoded = passwordEncoder.encode(password);

        userInfo.setUserName(userName);
        userInfo.setAuthority(authority);
        userInfo.setPassword(passwordEncoded);
        userInfo.setFullName(fullName);
        userInfo.setPhoneNumber(phoneNumber);
        userService.updateUser(userInfo);
        return "redirect:/admin/listOfUsers";
    }

    @PostMapping("/user/delete")
    public String deleteByUserId(@RequestParam("user.id") int userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin/listOfUsers";
    }

}
