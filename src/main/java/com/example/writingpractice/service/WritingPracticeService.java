// src/main/java/com/example/writingpractice/service/WritingPracticeService.java

package com.example.writingpractice.service;

import com.example.writingpractice.model.WritingPracticeQuestion;

public interface WritingPracticeService {

    WritingPracticeQuestion getWritingPracticeQuestion();

    WritingPracticeQuestion getWritingPracticeQuestionByTaskNumber(int taskNumber);

    void saveResponse(String clientName, String answerText, Long questionId);
}
