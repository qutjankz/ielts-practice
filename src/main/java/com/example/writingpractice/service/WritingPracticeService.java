// src/main/java/com/example/writingpractice/service/WritingPracticeService.java

package com.example.writingpractice.service;

import com.example.writingpractice.model.WritingPracticeQuestion;

public interface WritingPracticeService {

    WritingPracticeQuestion getWritingPracticeQuestion();

    void saveResponse(String clientName, String answerText);
}
