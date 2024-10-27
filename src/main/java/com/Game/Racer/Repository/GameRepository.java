package com.Game.Racer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Game.Racer.models.Game;

/**
 * GameRepository
 */
public interface GameRepository extends JpaRepository<Game, String> {
}
