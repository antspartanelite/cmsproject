package com.anthonynel.cmsproject.dao;

import com.anthonynel.cmsproject.dto.Blogpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDaoDB implements PostDao{

    @Autowired
    JdbcTemplate jdbc;


    public void addPost(Blogpost blogpost){
        jdbc.update("INSERT INTO Post(Content, Approved, PostTime) VALUES(?, ?, ?)",
                blogpost.getPost(),
                blogpost.isApproved(),
                blogpost.getPostTime());
    }

    @Override
    public List<Blogpost> getPosts() {
        List<Blogpost> heroes = jdbc.query("SELECT * FROM Post", new BlogPostMapper());
        return heroes;
    }

    //Maps Hero entries in the database to an object
    public static final class BlogPostMapper implements RowMapper<Blogpost> {

        @Override
        public Blogpost mapRow(ResultSet rs, int index) throws SQLException {
            Blogpost blogpost = new Blogpost();
            blogpost.setPost(rs.getString("Content"));
            blogpost.setApproved(rs.getBoolean("Approved"));
            blogpost.setPostTime(rs.getString("PostTime"));
            return blogpost;
        }
    }
}
