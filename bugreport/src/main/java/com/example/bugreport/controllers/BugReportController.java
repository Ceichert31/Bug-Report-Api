package com.example.bugreport.controllers;

import com.example.bugreport.records.BugReport;
import com.example.bugreport.services.BugReportService;
import org.springframework.web.bind.annotation.*;

/// Handles external requests
@RestController
@RequestMapping("/api/report")
public class BugReportController {
    private final BugReportService service;
    /// NOTE: DI service
    public BugReportController(BugReportService service){
        this.service = service;
    }

    // We don't need GET requests, just POST requests for sending info

    @PostMapping
    public BugReport createBugReport(@RequestBody BugReport report){
        // We will return the created bug report here
        return report;
    }
}