// src/main/java/com/example/writingpractice/service/WritingPracticeServiceImpl.java

package com.example.writingpractice.service;

import com.example.writingpractice.model.WritingPracticeQuestion;
import com.example.writingpractice.model.WritingPracticeResponse;
import com.example.writingpractice.repository.WritingPracticeQuestionRepository;
import com.example.writingpractice.repository.WritingPracticeResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WritingPracticeServiceImpl implements WritingPracticeService {

    private final WritingPracticeQuestionRepository questionRepository;
    private final WritingPracticeResponseRepository responseRepository;

    @Autowired
    public WritingPracticeServiceImpl(WritingPracticeQuestionRepository questionRepository, WritingPracticeResponseRepository responseRepository) {
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public WritingPracticeQuestion getWritingPracticeQuestion() {
        List<WritingPracticeQuestion> allQuestions = questionRepository.findAll();
        if (!allQuestions.isEmpty()) {
            int randomIndex = new Random().nextInt(allQuestions.size());
            return allQuestions.get(randomIndex);
        }
        return null;
    }

    @Override
    public void saveResponse(String clientName, String answerText) {
        WritingPracticeResponse response = new WritingPracticeResponse();
        response.setClientName(clientName);
        response.setAnswerText(answerText);
        responseRepository.save(response);
    }
}
