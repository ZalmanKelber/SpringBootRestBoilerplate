package com.simpleSBApps.restboilerlate.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainService {

    @Value("${welcome.message}")
    private String welcomeMessage;

    public String getMain() {
        return "main";
    }

    public String getGreeting() {
        return welcomeMessage;
    }
}
