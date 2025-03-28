package com.example.securitydemo.DAO;

import com.example.securitydemo.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends MongoRepository<User, String> {
    User findByEmail(String email);
}
