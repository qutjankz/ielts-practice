
package com.example.speakingpracticeapp.controller;

import com.example.speakingpracticeapp.model.QuestionPartTwo;
import com.example.speakingpracticeapp.model.QuestionPartOne;
import com.example.speakingpracticeapp.service.QuestionServicePartTwo;
import com.example.speakingpracticeapp.service.QuestionServicePartOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speaking")
public class PracticeController {

    private final QuestionServicePartTwo questionServicePartTwo;
    private final QuestionServicePartOne questionServicePartOne;

    @Autowired
    public PracticeController(QuestionServicePartTwo questionServicePartTwo, QuestionServicePartOne questionServicePartOne) {
        this.questionServicePartTwo = questionServicePartTwo;
        this.questionServicePartOne = questionServicePartOne;
    }

    @GetMapping("/part-1")
    public QuestionPartOne getRandomQuestionPartOne() {
        return questionServicePartOne.getRandomQuestionPartOne();
    }
    @GetMapping("/part-2")
    public QuestionPartTwo getRandomQuestionPartTwo() {
        return questionServicePartTwo.getRandomQuestionPartTwo();
    }
}