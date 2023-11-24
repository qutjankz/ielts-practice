// src/main/java/com/example/writingpractice/controller/WritingPracticeController.java

package com.example.writingpractice.controller;

import com.example.writingpractice.model.WritingPracticeQuestion;
import com.example.writingpractice.service.WritingPracticeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/writing")
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
    @GetMapping("/question/{taskNumber}")
    public WritingPracticeQuestion getWritingPracticeQuestionByTaskNumber(@PathVariable int taskNumber) {
        return writingPracticeService.getWritingPracticeQuestionByTaskNumber(taskNumber);
    }
    @PostMapping("/response")
    public void saveResponse(@RequestBody SaveResponseRequest request) {
        writingPracticeService.saveResponse(request.getClientName(), request.getAnswerText(), request.getQuestionId());
    }
    @Data
    static class SaveResponseRequest {
        private String clientName;
        private String answerText;
        private Long questionId;
    }
}
