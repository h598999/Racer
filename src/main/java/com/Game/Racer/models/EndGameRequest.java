package com.Game.Racer.models;


public class EndGameRequest {
    private String username;
    private String wpm;

    public EndGameRequest(String username, String wpm){
        this.username = username;
        this.wpm = wpm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWpm() {
        return wpm;
    }

    public void setWpm(String wpm) {
        this.wpm = wpm;
    }
}

