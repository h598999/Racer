package com.Game.Racer.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Game.Racer.models.User;
import com.Game.Racer.models.UserDTO;
import com.Game.Racer.service.UserService;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired UserService service;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user){
        Optional<User> retrievedUser = service.getUserByNameAndPass(user.getUsername(), user.getPassword());
        if (retrievedUser.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new UserDTO(retrievedUser.get()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user){
        User retrievedUser = service.addUser(user);
        return ResponseEntity.ok(new UserDTO(retrievedUser));
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable String username){
        Optional<User> user = service.findUserByUsername(username);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserDTO(user.get()));
    }

    
}
