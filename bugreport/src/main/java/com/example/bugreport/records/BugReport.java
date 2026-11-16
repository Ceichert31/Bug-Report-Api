package com.example.bugreport;

// Maybe we want to pass these things along?
// Screenshot
// Severity?
// User email/contact info
public record BugReport(String title, String message) {}