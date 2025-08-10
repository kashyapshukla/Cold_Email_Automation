package com.cold_email.cold_email.service;

// Importing required classes
import com.cold_email.cold_email.entity.EmailDetails;
import com.cold_email.cold_email.dto.BulkEmailRequest;
import com.cold_email.cold_email.dto.MailConfig;

// Interface
public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);

    String sendBulkMailWithAttachment(EmailDetails details);

    String sendBulk(BulkEmailRequest request);

    void updateConfig(MailConfig config);

    MailConfig getConfig();
}
