package com.simpleSBApps.restboilerlate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("basicvariables")
public class BasicConfig {

    private boolean value;
    private String message;
    private int amount;

    public boolean isValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public int getAmount() {
        return amount;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
