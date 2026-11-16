package com.example.bugreport.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Maybe we want to pass these things along?
// Screenshot
// Severity?
// User email/contact info
public record BugReport(
        @NotBlank @Size(max = 256) String title,
        @NotBlank @Size(max = 4000) String message,
        @Email @Size(max = 256) String email
) {}