package com.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(value = "/login.html")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/403.html")
    public String noPermission() {
        return "403";
    }

    @GetMapping(value = "/index.html")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/home.html")
    public String home() {
        return "home";
    }
}
