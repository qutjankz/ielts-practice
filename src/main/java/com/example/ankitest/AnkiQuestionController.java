package com.example.ankitest;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anki")
public class AnkiQuestionController {

    @Autowired
    private SrsService anki;


    @GetMapping("/question")
    public ResponseEntity<String> getQuestion() {
        AnkiQuestion ankiQuestion = anki.getNextQuestion();
        if (ankiQuestion != null) {
            String result = "Question: " + ankiQuestion.getQuestion() + " " +  ankiQuestion.getId();
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.ok("No more questions for today.");
        }
    }

    @PutMapping("/w/{id}")
    public ResponseEntity<String> markWrong(@PathVariable Long id) {
        boolean success = anki.updateSrsData(id, "w");

        if (success) {
            return ResponseEntity.ok("Marked as wrong");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/c/{id}")
    public ResponseEntity<String> markCorrect(@PathVariable Long id) {
        boolean success = anki.updateSrsData(id, "c");

        if (success) {
            return ResponseEntity.ok("Marked as correct");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/ask")
    public String askQuestion() {
        anki.ask();
        return "finish";
    }
}
