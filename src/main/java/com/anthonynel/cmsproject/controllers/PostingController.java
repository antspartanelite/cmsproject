package com.anthonynel.cmsproject.controllers;

import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostingController {
    @GetMapping("posterpage")
    public String displayLogin(){
        return "posterpage";
    }

    @PostMapping("createPost")
    public String createPost(Blogpost blogpost){
        System.out.println(blogpost.getPost());
        return "posterpage";
    }
}
