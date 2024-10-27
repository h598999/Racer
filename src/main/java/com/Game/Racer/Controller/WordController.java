package com.Game.Racer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Game.Racer.service.WordService;
/**
 * WordController
 */
@RequestMapping("api/v2/words")
@CrossOrigin
@RestController
public class WordController {
  
  @Autowired WordService service;

  @GetMapping("/ten")
  public ResponseEntity<List<String>> getTenWords(){
    List<String> words = service.getTenWords();
    if (words == null || words.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(words, HttpStatus.OK);
  }
}

