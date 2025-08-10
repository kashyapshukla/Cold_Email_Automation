package com.cold_email.cold_email.service;

// Java Program to Illustrate Creation Of
// Service implementation class


// Importing required classes
import com.cold_email.cold_email.entity.EmailDetails;
import com.cold_email.cold_email.dto.BulkEmailRequest;
import com.cold_email.cold_email.dto.MailConfig;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    private final MailConfig currentConfig = new MailConfig("smtp.gmail.com", 587, "", "", true, true, "");

    String send="";
    String email="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\n" +
            "</head>\n" +
            "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; color: #333;\">\n" +
            "<p>Hello,</p>\n" +
            "<p>I hope this email finds you well.</p>\n" +
            "<p>\n" +
            "    My name is <strong>xyz</strong>, and I am writing to express my interest in\n" +
            "    <strong>Software Engineer</strong> opportunities within your organization. With over 3 years of experience in\n" +
            "    developing, deploying, and maintaining projects using Java/J2EE and related technologies, I am excited about the\n" +
            "    opportunity to contribute to your team.\n" +
            "</p>\n" +
            "<p>A brief overview of my qualifications:</p>\n" +
            "<ul>\n" +
            "    <li>\n" +
            "        <strong>Strong Technical Skills:</strong> Proficient in Java, JavaScript, Python, HTML5, CSS, SQL, and Typescript,\n" +
            "        with experience in frameworks like React.js, Node.js, and Spring Boot.\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <strong>Project Experience:</strong> Currently at xyz, I integrated user-facing features, engineered APIs\n" +
            "        for seamless data flow, and optimized front-end performance. I also led initiatives in data privacy and automated\n" +
            "        database processes, ensuring high reliability and compliance.\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <strong>Efficiency Improvements:</strong> At zxy, I executed Agile methodologies, incorporated\n" +
            "        microservices architecture, and developed a single sign-on solution, resulting in significant reductions in project\n" +
            "        development time and server response time.\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <strong>Cloud Technologies:</strong> Hands-on experience with AWS and Google Cloud Platform, including services\n" +
            "        like DynamoDB, EC2, and CI/CD Pipelines. I also have experience with Docker, Jenkins, and Kubernetes for streamlined\n" +
            "        DevOps processes.\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <strong>Educational Background:</strong> I hold a Master’s degree in Computer Science from the xyz and a Bachelor’s in Electronics and Communications Engineering from zya.\n" +
            "    </li>\n" +
            "</ul>\n" +
            "<p>\n" +
            "    I am particularly drawn to your organization. I am confident that my background and skills align well with the\n" +
            "    requirements of your team, and I am eager to bring my expertise in software development and project management to\n" +
            "    your organization.\n" +
            "</p>\n" +
            "<p>\n" +
            "    Please find my resume attached for more detailed information about my background. I would welcome the opportunity to\n" +
            "    discuss how my experiences and skills can contribute to the continued success of your organization.\n" +
            "</p>\n" +
            "<p>Thank you for considering my application. I look forward to the possibility of discussing this exciting opportunity with you.</p>\n" +
            "<p>\n" +
            "    Best regards,<br>\n" +
            "    <strong>xyz</strong><br>\n" +
            "    Software Engineer<br>\n" +
            "    <a href=\"tel:+1111111111111\">12345678</a><br>\n" +
            "    <a href=\"mailto:\">abc@xyz.com</a><br>\n" +
            "    <a href=\"https://github.com/yourgithub\" target=\"_blank\">GitHub</a> |\n" +
            "    <a href=\"https://linkedin.com/in/yourlinkedin\" target=\"_blank\">LinkedIn</a> |\n" +
            "    <a href=\"https://leetcode.com/yourleetcode\" target=\"_blank\">LeetCode</a>\n" +
            "</p>\n" +
            "</body>\n" +
            "</html>\n";

    private JavaMailSender buildSenderFromConfig() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(currentConfig.getHost());
        if (currentConfig.getPort() != null) sender.setPort(currentConfig.getPort());
        sender.setUsername(currentConfig.getUsername());
        sender.setPassword(currentConfig.getPassword());
        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", String.valueOf(currentConfig.isAuth()));
        props.put("mail.smtp.starttls.enable", String.valueOf(currentConfig.isStarttls()));
        return sender;
    }

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(currentConfig.getFrom() != null && !currentConfig.getFrom().isEmpty() ? currentConfig.getFrom() : Optional.ofNullable(currentConfig.getUsername()).orElse(""));
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody() != null && !details.getMsgBody().isEmpty() ? details.getMsgBody() : email, true);
            helper.setSubject(details.getSubject());

            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail: " + e.getMessage();
        }
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(currentConfig.getFrom() != null && !currentConfig.getFrom().isEmpty() ? currentConfig.getFrom() : Optional.ofNullable(currentConfig.getUsername()).orElse(""));
            helper.setTo(details.getRecipient());
            helper.setText(details.getMsgBody() != null && !details.getMsgBody().isEmpty() ? details.getMsgBody() : email, true);
            helper.setSubject(details.getSubject());

            if (details.getAttachment() != null && !details.getAttachment().isEmpty()) {
                FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
                helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail: " + e.getMessage();
        }
    }

    @Override
    public String sendBulkMailWithAttachment(EmailDetails details) {

        List<String> emailList= sendBulkEmail(details);

        for( String email : emailList){
            details.setRecipient(email);
            sendMailWithAttachment(details);

        }

        return "Bulk mail sent";
    }

    public List<String> sendBulkEmail(EmailDetails details){

        String csvFilePath = details.getCsv();
        List<String> emailList = extractEmailAddresses(csvFilePath);

        emailList.forEach(System.out::println);

        return  emailList;
    }


    public static List<String> extractEmailAddresses(String filePath) {
        List<String> emailList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read the header line
            if (line == null) {
                System.out.println("The CSV file is empty.");
                return emailList;
            }

            String[] headers = line.split(","); // Assuming CSV is comma-separated
            int emailColumnIndex = -1;

            // Find the index of the "email" column
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase("email")) {
                    emailColumnIndex = i;
                    break;
                }
            }

            if (emailColumnIndex == -1) {
                System.out.println("No 'email' column found in the CSV file.");
                return emailList;
            }

            // Process the remaining lines
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > emailColumnIndex) {
                    String email = columns[emailColumnIndex].trim();
                    if (isValidEmail(email)) {
                        emailList.add(email);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return emailList;
    }

    // Helper method to validate email addresses
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Regex to match email addresses
        return email.matches(emailRegex);
    }

    @Override
    public String sendBulk(BulkEmailRequest request) {
        int sent = 0;
        for (String to : Optional.ofNullable(request.getRecipients()).orElse(Collections.emptyList())) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(currentConfig.getFrom() != null && !currentConfig.getFrom().isEmpty() ? currentConfig.getFrom() : Optional.ofNullable(currentConfig.getUsername()).orElse(""));
                helper.setTo(to);
                helper.setSubject(request.getSubject());
                helper.setText(request.getBody() != null && !request.getBody().isEmpty() ? request.getBody() : email, true);
                javaMailSender.send(mimeMessage);
                sent++;
            } catch (Exception ignored) {}
        }
        return "Sent " + sent + " emails";
    }

    @Override
    public void updateConfig(MailConfig config) {
        if (config.getHost() != null) currentConfig.setHost(config.getHost());
        if (config.getPort() != null) currentConfig.setPort(config.getPort());
        if (config.getUsername() != null) currentConfig.setUsername(config.getUsername());
        if (config.getPassword() != null) currentConfig.setPassword(config.getPassword());
        currentConfig.setAuth(config.isAuth());
        currentConfig.setStarttls(config.isStarttls());
        if (config.getFrom() != null) currentConfig.setFrom(config.getFrom());

        // If the bean JavaMailSender is not configured with credentials, we can dynamically update it
        if (javaMailSender instanceof JavaMailSenderImpl impl) {
            impl.setHost(currentConfig.getHost());
            if (currentConfig.getPort() != null) impl.setPort(currentConfig.getPort());
            impl.setUsername(currentConfig.getUsername());
            impl.setPassword(currentConfig.getPassword());
            Properties props = impl.getJavaMailProperties();
            props.put("mail.smtp.auth", String.valueOf(currentConfig.isAuth()));
            props.put("mail.smtp.starttls.enable", String.valueOf(currentConfig.isStarttls()));
        }
    }

    @Override
    public MailConfig getConfig() {
        return currentConfig;
    }
}

