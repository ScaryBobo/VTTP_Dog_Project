package com.example.DogProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.support.rowset.SqlRowSet;
@Data
public class User {
    Integer userId;
    String username;
    String password;
    String email;


    public User() {
    }
    
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public static User create(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
       
        return user;
    }

    
}
