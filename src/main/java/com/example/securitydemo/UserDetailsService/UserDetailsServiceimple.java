package com.example.securitydemo.UserDetailsService;

import com.example.securitydemo.DAO.UserDAO;
import com.example.securitydemo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceimple implements UserDetailsService {
    @Autowired
    private UserDAO repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found sike");
        }
        return new UserPrincipal(user);
    }
}
