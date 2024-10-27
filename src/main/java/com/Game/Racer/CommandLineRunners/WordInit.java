package com.Game.Racer.CommandLineRunners;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Game.Racer.Repository.WordRepository;
import com.Game.Racer.models.Word;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class WordInit implements CommandLineRunner {

    @Autowired WordRepository repository;

    @Override
    public void run(String... args) throws Exception {
        loadWordsFromFile("./words.txt");
    }

    private void loadWordsFromFile(String filename){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words.txt");
        if (inputStream == null){
            throw new IllegalArgumentException("File not found Exception: words.txt");
        }

        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)){
            while (scanner.hasNextLine()) {
                Word word = new Word(scanner.nextLine());
                repository.save(word);
            }
        }
    }


}

