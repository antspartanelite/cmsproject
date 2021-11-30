package com.anthonynel.cmsproject.controllers;

import com.anthonynel.cmsproject.dao.PostDao;
import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    PostDao postDao;

    @GetMapping("guestpage")
    public String displayBlog(Model model){
        List<Blogpost> blogposts = postDao.getPosts();
        model.addAttribute("blogposts", blogposts);
        return "guestpage";
    }
}
