package com.Game.Racer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Game.Racer.models.Word;

/**
 * WordRepository
 */
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w WHERE w.id > ?1 AND w.id < ?2")
    List<Word> findWordsByIdRange(Long x, Long y);
}
