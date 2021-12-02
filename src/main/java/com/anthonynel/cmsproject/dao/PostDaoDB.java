package com.anthonynel.cmsproject.dao;

import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostDaoDB implements PostDao{

    @Autowired
    JdbcTemplate jdbc;

    //Adds a new post to the database
    public void addPost(Blogpost blogpost){
        jdbc.update("INSERT INTO Post(Content, Approved, PostTime) VALUES(?, ?, CURRENT_TIMESTAMP)",
                blogpost.getPost(),
                blogpost.isApproved());
    }

    //Gets all posts from the database
    @Override
    public List<Blogpost> getPosts() {
        List<Blogpost> posts = jdbc.query("SELECT * FROM Post ORDER BY PostTime DESC", new BlogPostMapper());
        return posts;
    }

    //Sets a specific post to approved
    @Override
    public void setPostApproved(int postId) {
        jdbc.update("UPDATE Post SET Approved = true WHERE PostID = ?", postId);
    }

    //Gets all posts with a specific tag
    @Override
    public List<Blogpost> getPostsWithTag(String tag) {
        List<Blogpost> posts = jdbc.query("SELECT * FROM PostTag INNER JOIN Post ON Post.PostID = PostTag.PostID" +
                " WHERE Tag = ? ORDER BY PostTime DESC", new BlogPostMapper(), tag);
        return posts;
    }

    //Gets all approved posts with a specific tag
    @Override
    public List<Blogpost> getApprovedPostsWithTag(String tag) {
        List<Blogpost> posts = jdbc.query("SELECT * FROM PostTag INNER JOIN Post ON Post.PostID = PostTag.PostID" +
                " WHERE Tag = ? AND Approved IS true ORDER BY PostTime DESC", new BlogPostMapper(), tag);
        return posts;
    }

    //Sets the tags for a post
    @Override
    public void setPostTags(int id, String[] tags) {
        for(String tag: tags){
            jdbc.update("INSERT INTO PostTag(PostID, Tag) VALUES(?, ?)", id, tag);
        }
    }

    //Gets all tags in the DB
    @Override
    public List<String> getTags() {
        return jdbc.query("SELECT Tag FROM PostTag" + " GROUP BY Tag", new TagMapper());
    }

    //Maps Hero entries in the database to an object
    public static final class BlogPostMapper implements RowMapper<Blogpost> {

        @Override
        public Blogpost mapRow(ResultSet rs, int index) throws SQLException {
            Blogpost blogpost = new Blogpost();
            blogpost.setPostId(rs.getInt("postID"));
            blogpost.setPost(rs.getString("Content"));
            blogpost.setApproved(rs.getBoolean("Approved"));
            blogpost.setPostTime(rs.getString("PostTime"));
            return blogpost;
        }
    }

    //Maps Hero entries in the database to an object
    public static final class TagMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            return rs.getString("Tag");
        }
    }
}
