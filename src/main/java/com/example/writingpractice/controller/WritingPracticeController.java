// src/main/java/com/example/writingpractice/controller/WritingPracticeController.java

package com.example.writingpractice.controller;

import com.example.writingpractice.model.WritingPracticeQuestion;
import com.example.writingpractice.service.WritingPracticeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice/writing")
public class WritingPracticeController {

    private final WritingPracticeService writingPracticeService;

    @Autowired
    public WritingPracticeController(WritingPracticeService writingPracticeService) {
        this.writingPracticeService = writingPracticeService;
    }

    @GetMapping("/question")
    public WritingPracticeQuestion getWritingPracticeQuestion() {
        return writingPracticeService.getWritingPracticeQuestion();
    }

    @PostMapping("/response")
    public void saveResponse(@RequestBody SaveResponseRequest request) {
        if (request.getClientName() == null || request.getAnswerText() == null) {
            // Handle the case where either clientName or answerText is null
            throw new IllegalArgumentException("clientName and answerText must not be null");
        }

        writingPracticeService.saveResponse(request.getClientName(), request.getAnswerText());
    }
    @Data
    static class SaveResponseRequest {
        private String clientName;
        private String answerText;
    }
}
