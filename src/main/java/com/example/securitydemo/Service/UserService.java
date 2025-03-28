package com.example.securitydemo.Service;

import com.example.securitydemo.DAO.UserDAO;
import com.example.securitydemo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userRepo;
    @Autowired
    public UserService(UserDAO u){
        userRepo = u;
    }
    @Autowired
    public AuthenticationManager authManager;
    @Autowired
    public JWTService jwtService;
    public List<User> findEveryOne(){
        return userRepo.findAll();
    }
    public User findById(String id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }
    public User createUser(User u){
        return userRepo.save(u);
    }

    public String verify(User u) {
        System.out.println("before");
        try{
            Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPassword()));

            System.out.println("after");
            if(authentication.isAuthenticated()){

                return jwtService.generateToken(u.getEmail());
            }
            return "failured";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "failure";
        }
    }
}
