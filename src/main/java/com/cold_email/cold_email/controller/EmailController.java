package com.cold_email.cold_email.controller;

// Java Program to Create Rest Controller that
// Defines various API for Sending Mail


// Importing required classes
import com.cold_email.cold_email.entity.EmailDetails;
import com.cold_email.cold_email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendMailWithAttachment(details);

        return status;
    }

    //csv
    @PostMapping("/sendBulkMailWithAttachment")
    public String sendBulkMailWithAttachment(
            @RequestBody EmailDetails details)
    {
        String status
                = emailService.sendBulkMailWithAttachment(details);

        return status;
    }
}
