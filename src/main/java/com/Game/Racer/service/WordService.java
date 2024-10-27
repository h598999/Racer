package com.Game.Racer.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Game.Racer.Repository.WordRepository;
import com.Game.Racer.models.Word;

/**
 * WordService
 */
@Service
public class WordService {


    @Autowired WordRepository repository;
    
    public List<String> getTenWords(){
        List<Word> words = repository.findAll();
        Long StartIndex = new Random().nextLong(words.size()-10);
        return repository.findWordsByIdRange(StartIndex, StartIndex+10)
            .stream()
            .map(Word::getWord)
            .toList();
    }
    
}
