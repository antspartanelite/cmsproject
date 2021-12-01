package com.anthonynel.cmsproject.dao;

import com.anthonynel.cmsproject.dto.Blogpost;

import java.util.List;

public interface PostDao {
    public void addPost(Blogpost blogpost);
    public List<Blogpost> getPosts();
}
