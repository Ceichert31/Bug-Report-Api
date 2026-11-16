package com.example.bugreport.services;

import com.example.bugreport.records.BugReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpHeaders;


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


    public void send(BugReport report){

    }
}