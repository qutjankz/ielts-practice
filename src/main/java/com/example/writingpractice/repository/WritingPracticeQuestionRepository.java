// src/main/java/com/example/writingpractice/repository/WritingPracticeQuestionRepository.java

package com.example.writingpractice.repository;

import com.example.writingpractice.model.WritingPracticeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingPracticeQuestionRepository extends JpaRepository<WritingPracticeQuestion, Long> {
}
