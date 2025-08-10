package com.cold_email.cold_email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkEmailRequest {
    private List<String> recipients;
    private String subject;
    private String body;
}