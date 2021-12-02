package com.anthonynel.cmsproject.dao;

import com.anthonynel.cmsproject.dto.Blogpost;

import java.util.List;

public interface PostDao {
    public void addPost(Blogpost blogpost);
    public List<Blogpost> getPosts();
    public List<Blogpost> getPostsWithTag(String tag);
    public List<Blogpost> getApprovedPostsWithTag(String tag);
    public List<String> getTags();
    public void setPostTags(int id, String[] tags);
    public void setPostApproved(int postId);
}
