package com.Game.Racer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Game.Racer.models.User;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username=?1")
    public Optional<User> findByUsername(String username);

    
}
