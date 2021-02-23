package com.dachshundcompany.devops.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devops")
public class DevopsRestController {
    
    @RequestMapping("/hello")
    public String getCurrentDate() {

        return "Hello World!";
    }
}
