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
    private final RestClient discord;

    public BugReportService(@Value("${discord.bot-token}") String botToken)
    {
        this.discord = RestClient.builder()
                .baseUrl("https://discord.com/api/v10")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bot " + botToken)
                .build();
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
                .uri("/channels/{id}/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}