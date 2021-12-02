package com.anthonynel.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class LoginController {
    @GetMapping("login")
    public String displayLogin(){
        return "login";
    }

    @PostMapping("login")
    public String checkCredentials(String username, String password){
        if(Objects.equals(username, "owner") && Objects.equals(password, "password")) {
            return "redirect:/ownerposterpage";
        }
        if(Objects.equals(username, "guest") && Objects.equals(password, "password")) {
            return "redirect:/posterpage";
        }
        return "redirect:/login";
    }
}
