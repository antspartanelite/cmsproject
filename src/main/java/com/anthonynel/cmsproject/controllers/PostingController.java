package com.anthonynel.cmsproject.controllers;

import com.anthonynel.cmsproject.dao.PostDao;
import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostingController {

    @Autowired
    PostDao postDao;

    @GetMapping("posterpage{tag}")
    public String displayGuestPostPage(@PathVariable String tag, Model model){
        Blogpost blogpost = new Blogpost();
        model.addAttribute("blogpost", blogpost);
        List<Blogpost> blogposts = postDao.getPostsWithTag(tag);
        model.addAttribute("blogposts", blogposts);
        return "posterpage";
    }

    @PostMapping("createPost")
    public String createGuestPost(Blogpost blogpost, String tags){
        blogpost.setApproved(false);
        blogpost.setPostTime("2013-03-03 19:22:11");
        postDao.addPost(blogpost);
        return "redirect:/posterpage";
    }

    @GetMapping("ownerposterpage{tag}")
    public String displayOwnerPostPage(@PathVariable String tag, Model model){
        Blogpost blogpost = new Blogpost();
        model.addAttribute("blogpost", blogpost);
        List<Blogpost> blogposts = postDao.getPostsWithTag(tag);
        model.addAttribute("blogposts", blogposts);
        return "ownerposterpage";
    }

    @PostMapping("createOwnerPost")
    public String createOwnerPost(Blogpost blogpost, String tags){
        blogpost.setApproved(true);
        blogpost.setPostTime("2013-03-03 19:22:11");
        postDao.addPost(blogpost);

        String[] tagList = tags.split("#");
        postDao.setPostTags(postDao.getPosts().get(0).getPostId(), tagList);
        return "redirect:/ownerposterpage";
    }

    @PostMapping("setApproved")
    public String setApproved(int postId){
        postDao.setPostApproved(postId);
        return "redirect:/ownerposterpage";
    }
}
