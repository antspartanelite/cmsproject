package com.anthonynel.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String displayLogin(){
        return "login";
    }

    @PostMapping("login")
    public String checkCredentials(){
        return "redirect:/posterpage";
    }
}
