package com.anthonynel.cmsproject.controllers;

import com.anthonynel.cmsproject.dao.PostDao;
import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostingController {

    @Autowired
    PostDao postDao;

    @GetMapping("posterpage")
    public String displayLogin(Model model){
        Blogpost blogpost = new Blogpost();
        model.addAttribute("blogpost", blogpost);
        List<Blogpost> blogposts = postDao.getPosts();
        model.addAttribute("blogposts", blogposts);
        return "posterpage";
    }

    @PostMapping("createPost")
    public String createPost(Blogpost blogpost){
        blogpost.setApproved(false);
        blogpost.setPostTime("2013-03-03 19:22:11");
        postDao.addPost(blogpost);
        return "redirect:/posterpage";
    }
}
