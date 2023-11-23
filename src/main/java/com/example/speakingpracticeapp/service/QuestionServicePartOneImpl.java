package com.example.speakingpracticeapp.service;
import com.example.speakingpracticeapp.model.QuestionPartOne;
import com.example.speakingpracticeapp.repository.QuestionRepositoryPartOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServicePartOneImpl implements QuestionServicePartOne {

    private final QuestionRepositoryPartOne questionRepositoryPartOne;

    @Autowired
    public QuestionServicePartOneImpl(QuestionRepositoryPartOne questionRepositoryPartOne) {
        this.questionRepositoryPartOne = questionRepositoryPartOne;
    }

    @Override
    public QuestionPartOne getRandomQuestionPartOne() {
        List<QuestionPartOne> allQuestionPartTwos = questionRepositoryPartOne.findAll();
        if (!allQuestionPartTwos.isEmpty()) {
            int randomIndex = new Random().nextInt(allQuestionPartTwos.size());
            return allQuestionPartTwos.get(randomIndex);
        }
        return null;
    }
}