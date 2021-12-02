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

    //Displays the guest poster's post page with posts filtered by a tag
    @GetMapping("posterpage{tag}")
    public String displayGuestPostPage(@PathVariable String tag, Model model){
        Blogpost blogpost = new Blogpost();
        model.addAttribute("blogpost", blogpost);
        List<Blogpost> blogposts = postDao.getPostsWithTag(tag);
        model.addAttribute("blogposts", blogposts);
        model.addAttribute("tags",postDao.getTags());
        return "posterpage";
    }

    //Creates a post in the database pending the owner's approval then refreshes the page
    @PostMapping("createPost")
    public String createGuestPost(Blogpost blogpost, String tags){
        blogpost.setApproved(false);
        postDao.addPost(blogpost);

        String[] tagList = tags.split("#");
        postDao.setPostTags(postDao.getPosts().get(0).getPostId(), tagList);
        return "redirect:/posterpage";
    }

    //Displays the full admin post page with posts filtered by a tag
    @GetMapping("ownerposterpage{tag}")
    public String displayOwnerPostPage(@PathVariable String tag, Model model){
        Blogpost blogpost = new Blogpost();
        model.addAttribute("blogpost", blogpost);
        List<Blogpost> blogposts = postDao.getPostsWithTag(tag);
        model.addAttribute("blogposts", blogposts);
        model.addAttribute("tags",postDao.getTags());
        return "ownerposterpage";
    }

    //Creates a new post and approves it then refreshes the page
    @PostMapping("createOwnerPost")
    public String createOwnerPost(Blogpost blogpost, String tags){
        blogpost.setApproved(true);
        postDao.addPost(blogpost);

        String[] tagList = tags.split("#");
        postDao.setPostTags(postDao.getPosts().get(0).getPostId(), tagList);
        return "redirect:/ownerposterpage";
    }

    //Sets a post to approved
    @PostMapping("setApproved")
    public String setApproved(int postId){
        postDao.setPostApproved(postId);
        return "redirect:/ownerposterpage";
    }
}
