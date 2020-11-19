package com.simpleSBApps.restboilerlate.controllers;

import com.simpleSBApps.restboilerlate.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/main", method = GET)
    public String getMain() {
        return mainService.getMain();
    }

}
