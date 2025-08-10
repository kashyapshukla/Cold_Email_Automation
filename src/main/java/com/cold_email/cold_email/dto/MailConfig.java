package com.cold_email.cold_email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailConfig {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private boolean auth;
    private boolean starttls;
    private String from;
}