package com.example.DogProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeneratedImageRepository {
    @Autowired
    private JdbcTemplate template;

    public boolean insertNewImageRecord(Integer userId, String username, String imageUrl){
        final int rowcount = template.update("insert into user (user_id, username, password) values (?,?,?)" 
                                            ,userId, username, imageUrl);
        return rowcount > 0;
    }
}
