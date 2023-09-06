package com.example.springbackend.controller;

import com.example.springbackend.entity.UserInfo;
import com.example.springbackend.service.EmailConfirmationService;
import com.example.springbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/findById/{userId}")
    public UserInfo findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/user/findByUsername/{userName}")
    public UserInfo findUserByUserName(@PathVariable("userName") String userName) {

        return userService.findUserByUserName(userName);
    }

    @GetMapping("/user/findByPhoneNumber/{phoneNumber}")
    public UserInfo findUserByPhoneNumber(@PathVariable("phoneNumber") int phoneNumber) {

        return userService.findUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/user/getAllUsers")
    public List<UserInfo> getAllUsers() {
        return userService.findAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user")
    public void createUser(@ModelAttribute("user") UserInfo userInfo) {
        userInfo.setAuthority("ROLE_USER");
        userInfo.setEnabled(true);
        userService.saveUser(userInfo);
    }

//    @PutMapping("/user")
//    public void updateUser(@RequestBody UserInfo userInfo) {
//        userService.updateUser(userInfo);
//    }



//    @DeleteMapping("/user/delete/userName/{username}")
//    public void deleteByUserName(@PathVariable("username") String userName) {
//        userService.deleteUserByUserName(userName);
//    }


//    @DeleteMapping("/user/delete/userId/{id}")
//    public void deleteByUserId(@PathVariable("id") int userId) {
//        userService.deleteUserById(userId);
//    }


//    @PostMapping("/user/transferMoney")
//    public String transferMoney(@RequestParam String nameOfAccountSend, @RequestParam String nameOfAccountReceive, @RequestParam  float amount) {
//        userService.transferMoney(nameOfAccountSend, nameOfAccountReceive, amount);
//        return "redirect:/default";
//    }

    @PostMapping("/user/withDraw")
    public ResponseEntity<String> withDrawMoney(@RequestParam String nameOfAccountWithDraw, @RequestParam float amount) {
        return userService.withDrawMoney(nameOfAccountWithDraw, amount);
    }

    @PostMapping("/user/payThePill")
    public ResponseEntity<String> payTheBill(@RequestParam String nameOfAccountPayTheBill, @RequestParam float amount) {
        return userService.payTheBill(nameOfAccountPayTheBill, amount);
    }



}
