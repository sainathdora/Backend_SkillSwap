package com.example.securitydemo.RestController;

import com.example.securitydemo.Entity.User;
import com.example.securitydemo.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PutMapping("/updateUser")
    public String Update_user(@RequestBody User u){
        System.out.println("Hitting update user");
        String id = u.getId();
        try{
            userserviceObj.UpdateByID(u, id);
            return "Success";
        }catch (RuntimeException e){
            return "Failed";
        }

    }
    @GetMapping("/users")
    public List<User> findall(){
        return userserviceObj.findEveryOne();
    }
    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable String email){
        System.out.println("fdnf d");
        return userserviceObj.findByEmail(email);
    }
    @PostMapping("/registor")
    public User registor(@RequestBody User u){
        u.setPassword(encoder.encode(u.getPassword()));
        return userserviceObj.createUser(u);
    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User u){
        System.out.println(u+" Nigga");
        Map<String, Object> mp = new HashMap<>();
        String res = userserviceObj.verify(u);
        mp.put("result", res);
        if(res.equals("failure")){
            return mp;
        }
        User user = userserviceObj.findByEmail(u.getEmail());
        mp.put("user",user);
        return mp;
    }
}
