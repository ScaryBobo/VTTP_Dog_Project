package com.example.DogProject.repository;

import java.util.ArrayList;
import java.util.List;


import com.example.DogProject.model.Dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class GeneratedImageRepository {
    @Autowired
    private JdbcTemplate template;
    
    public static final String SQL_RANDOM_IMG_RECORD = "insert into generated_images_tbl(user_id, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament, imageUrl) values(?,?,?,?,?,?,?,?,?)";
    public static final String SQL_RANDOM_QUERY_RECORD = "insert into generated_images_tbl(user_id, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament) values(?,?,?,?,?,?,?,?)";
    public boolean insertNewRandomImgRecord(Integer userId, String dogName, String dogHeight,
                                    String dogWeight, String bredPurpose, String breedGroup,
                                    String lifespan, String temperament, String imageUrl){
                                        
        
        final int rowcount = template.update(SQL_RANDOM_IMG_RECORD, userId, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament, imageUrl);
        return rowcount > 0;
    }

    public boolean insertNewQueryRecord(Integer userId, String dogName, String dogHeight,
                                    String dogWeight, String bredPurpose, String breedGroup,
                                    String lifespan, String temperament){

                                        
        final int rowcount = template.update(SQL_RANDOM_QUERY_RECORD, userId, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament);
        return rowcount > 0;
    }
    

    public List<Dog> getLatestSearchByUserId(Integer userId){
        
        final List<Dog> dogList = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(
            "select * from generated_images_tbl  where user_id like ? order by user_id desc limit 1",userId);
       while (rs.next()){
            Dog dog = new Dog();
            dog.setDogName(rs.getString("dogName"));
            dog.setDogHeight(rs.getString("dogHeight"));
            dog.setDogWeight(rs.getString("dogWeight"));
            dog.setBredPurpose(rs.getString("bredPurpose"));
            dog.setBreedGroup(rs.getString("breedGroup"));
            dog.setLifeSpan(rs.getString("lifespan"));
            dog.setTemperament(rs.getString("temperament"));
            dog.setImageUrl(rs.getString("imageUrl"));

            dogList.add(dog);
        }
        return dogList;

    }

    public List<Dog> getLatestFiveSearchByUserId(Integer userId){
        final List<Dog> dogList = new ArrayList<>();
        final SqlRowSet rs = template.queryForRowSet(
            "select * from generated_images_tbl where user_id like ? order by timestamp desc limit 5", userId
        );
        while (rs.next()){
            Dog dog = new Dog();
            dog.setDogName(rs.getString("dogName"));
            dog.setDogHeight(rs.getString("dogHeight"));
            dog.setDogWeight(rs.getString("dogWeight"));
            dog.setBredPurpose(rs.getString("bredPurpose"));
            dog.setBreedGroup(rs.getString("breedGroup"));
            dog.setLifeSpan(rs.getString("lifespan"));
            dog.setTemperament(rs.getString("temperament"));
            dog.setImageUrl(rs.getString("imageUrl"));

            dogList.add(dog);

        }
        return dogList;

    }

}

