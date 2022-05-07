package com.example.DogProject.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    Integer userId;
    String username;
    String password;
    String email;


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(int i) {
        this.userId = i;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
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
