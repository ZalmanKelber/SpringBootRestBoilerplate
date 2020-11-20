package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.services.BasicService;
import com.simpleSBApps.restboilerlate.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @Autowired
    BasicService basicService;

    @RequestMapping(value = "/main", method = GET)
    public String getMain() {
        return mainService.getMain();
    }

    @RequestMapping(value = "/welcome", method = GET)
    public String getWelcome() {
        return mainService.getGreeting();
    }

    @RequestMapping(value = "/basic", method = GET)
    public Map<String, Object> getBasic() {
        return basicService.getMap();
    }

}
