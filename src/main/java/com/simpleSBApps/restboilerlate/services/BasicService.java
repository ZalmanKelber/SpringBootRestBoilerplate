package com.simpleSBApps.restboilerlate.services;

import com.simpleSBApps.restboilerlate.config.BasicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BasicService {

    @Autowired
    BasicConfig basicConfig;

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("value", basicConfig.isValue());
        map.put("message", basicConfig.getMessage());
        map.put("amount", basicConfig.getAmount());
        return map;
    }
}
