package com.example.securitydemo.RestController;

import com.example.securitydemo.Entity.User;
import com.example.securitydemo.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class api {
    private final UserService userserviceObj;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    public api(UserService us){
        userserviceObj = us;
    }
    @GetMapping("/")
    public String sayHello(HttpServletRequest request){
        return "Hello word "+request.getSession().getId()+". ";
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        System.out.println(id);
        return userserviceObj.findById(id);
    }
    @GetMapping("/users")
    public List<User> findall(){
        return userserviceObj.findEveryOne();
    }
    @PostMapping("/registor")
    public User registor(@RequestBody User u){
        u.setPassword(encoder.encode(u.getPassword()));
        return userserviceObj.createUser(u);
    }
    @PostMapping("/login")
    public String login(@RequestBody User u){
        System.out.println(u+" Nigga");
        return userserviceObj.verify(u);
    }
}
