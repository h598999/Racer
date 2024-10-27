package com.Game.Racer.models;

import java.util.List;

/**
 * UserDTO
 */
public class UserDTO {

    private Long id;
    private String username;
    private List<Game> gamesPlayed;

    public UserDTO(){}

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.gamesPlayed = user.getGamesPlayed();
    }

    public UserDTO(Long id, String username, List<Game> gamesPlayed){
        this.id = id;
        this.username = username;
        this.gamesPlayed = gamesPlayed;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<Game> getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(List<Game> gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
