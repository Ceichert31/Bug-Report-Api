package com.example.bugreport.services;

import com.example.bugreport.records.BugReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpHeaders;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/// Handles interfacing with Discord API and sending bug report
@Service
public class BugReportService {
    private final RestClient discord = RestClient.create();
    private final String webhookUrl;

    public BugReportService(@Value("${discord.webhook-url}") String webhookUrl)
    {
        this.webhookUrl = webhookUrl;
    }


    /// Interfaces with the Discord API to send a message in a channel
    public void send(BugReport report){
        List<Map<String, Object>> fields = new ArrayList<>();

        Map<String, Object> embed = Map.of(
                "title", report.title(),
                "description", report.message(),
                "fields", fields,
                "timestamp", Instant.now().toString()
        );

        Map<String, Object> body = Map.of(
                "embeds", List.of(embed),
                "allowed_mentions", Map.of("parse", List.of())
        );

        discord.post()
                .uri(webhookUrl + "?wait=true")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}