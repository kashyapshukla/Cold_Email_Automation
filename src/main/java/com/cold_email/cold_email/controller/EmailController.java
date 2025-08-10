package com.cold_email.cold_email.controller;

// Java Program to Create Rest Controller that
// Defines various API for Sending Mail


// Importing required classes
import com.cold_email.cold_email.entity.EmailDetails;
import com.cold_email.cold_email.service.EmailService;
import com.cold_email.cold_email.dto.BulkEmailRequest;
import com.cold_email.cold_email.dto.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }

    // csv
    @PostMapping("/sendBulkMailWithAttachment")
    public String sendBulkMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendBulkMailWithAttachment(details);
    }

    // Bulk send list of emails
    @PostMapping("/sendBulk")
    public String sendBulk(@RequestBody BulkEmailRequest request) {
        return emailService.sendBulk(request);
    }

    // Update mail configuration
    @PostMapping("/config")
    public void updateConfig(@RequestBody MailConfig config) {
        emailService.updateConfig(config);
    }

    // Get current configuration (without password ideally)
    @GetMapping("/config")
    public MailConfig getConfig() {
        MailConfig cfg = emailService.getConfig();
        // Do not expose password
        return new MailConfig(cfg.getHost(), cfg.getPort(), cfg.getUsername(), null, cfg.isAuth(), cfg.isStarttls(), cfg.getFrom());
    }
}
