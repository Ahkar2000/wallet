package com.ahkar.controller;

import com.ahkar.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @GetMapping("/{userId}")
    public String emailSend(@PathVariable("userId") Long userId){
        emailService.sendEmail(userId);
        return "email sent.";
    }
}
