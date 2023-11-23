package com.example.speakingpracticeapp.service;
import com.example.speakingpracticeapp.model.QuestionPartTwo;
import com.example.speakingpracticeapp.repository.QuestionRepositoryPartTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServicePartTwoImpl implements QuestionServicePartTwo {

    private final QuestionRepositoryPartTwo questionRepositoryPartTwo;

    @Autowired
    public QuestionServicePartTwoImpl(QuestionRepositoryPartTwo questionRepositoryPartTwo) {
        this.questionRepositoryPartTwo = questionRepositoryPartTwo;
    }

    @Override
    public QuestionPartTwo getRandomQuestionPartTwo() {
        List<QuestionPartTwo> allQuestionPartTwos = questionRepositoryPartTwo.findAll();
        if (!allQuestionPartTwos.isEmpty()) {
            int randomIndex = new Random().nextInt(allQuestionPartTwos.size());
            return allQuestionPartTwos.get(randomIndex);
        }
        return null;
    }
}