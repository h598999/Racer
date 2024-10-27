package com.Game.Racer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.Game.Racer.Repository.UserRepository;
import com.Game.Racer.models.User;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired UserRepository repo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User addUser(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return repo.save(user);
    }

    
    public Optional<User> findUserByUsername(String username){
        return repo.findByUsername(username);
    }

    public Optional<User> getUserByNameAndPass(String username,String password) {
        String safeUsername = HtmlUtils.htmlEscape(username);
        String safePassword = HtmlUtils.htmlEscape(password);
        Optional<User> retrievedUser = repo.findByUsername(safeUsername);


        if (retrievedUser.isPresent()) {
            User user = retrievedUser.get();
            // Compare the provided password with the stored hashed password
            if (passwordEncoder.matches(safePassword, user.getPassword())) {
                return Optional.of(user); // Return the user if password matches
            }
        }
        return Optional.empty();
    }

    
    
}

