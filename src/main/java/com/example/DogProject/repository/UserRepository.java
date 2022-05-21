package com.example.DogProject.repository;

import java.util.Optional;

import com.example.DogProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;


    public Optional<User> getUserByUserId(Integer userId) {
  
        final SqlRowSet result = template.queryForRowSet(
            "select * from user where userId like ?", userId
        );
        if (!result.next())
            return Optional.empty();

        return Optional.of(User.create(result));
    }


    public boolean insertNewUser(Integer userId, String username, String password, String email){
        final int rowcount = template.update("insert into user (user_id, username, password, email) values (?,?,?,?)" 
                                            ,userId, username, password, email);
        return rowcount > 0;
    }
   
    public Optional<Integer> getUserIdByUsername(String username) {
  
        final SqlRowSet result = template.queryForRowSet(
            "select user_id from user where username like ?", username
        );
        if (!result.next())
            return Optional.empty();

        return Optional.of(result.getInt("user_id"));
    }
    public Optional<String> getUserRecordByUsername(String username) {
  
        final SqlRowSet result = template.queryForRowSet(
            "select username from user where username like ?", username
        );
        if (!result.next())
            return Optional.empty();

        return Optional.of(result.getString("username"));
    }

    public Optional<String> getUserRecordByEmail(String email) {
  
        final SqlRowSet result = template.queryForRowSet(
            "select email from user where email like ?", email
        );
        if (!result.next())
            return Optional.empty();

        return Optional.of(result.getString("email"));
    }
    

    

}
