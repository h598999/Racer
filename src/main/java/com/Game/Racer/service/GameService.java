package com.Game.Racer.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Game.Racer.Repository.GameRepository;
import com.Game.Racer.Repository.UserRepository;
import com.Game.Racer.models.EndGameRequest;
import com.Game.Racer.models.Game;
import com.Game.Racer.models.User;


/**
 * GameService
 */
@Service
public class GameService {

    @Autowired GameRepository repo;
    @Autowired UserRepository uRepo;

    public Game StartNewGame(){
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setStartTime(LocalDateTime.now());
        return repo.save(game);
    }

    public Game endGame(String gameId, EndGameRequest request){
        Optional<Game> game = repo.findById(gameId);
    
    // Check if the game exists
    if (game.isEmpty()) {
        return null; // Game not found
    }
    
    Game existingGame = game.get();

    // Add the user if the username is provided
    if (request.getUsername() != null) {
        Optional<User> userOpt = uRepo.findByUsername(request.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.getGamesPlayed().add(existingGame);
            existingGame.getPlayers().add(user);
            uRepo.save(user);
        } 
    }

    // Update game properties
    existingGame.setEndTime(LocalDateTime.now());
    existingGame.setWPM(Integer.parseInt(request.getWpm()));

    // Save the updated game
    return repo.save(existingGame); // Save the modified game
    }
    
}

