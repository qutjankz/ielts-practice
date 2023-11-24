// src/main/java/com/example/writingpractice/repository/WritingPracticeQuestionRepository.java

package com.example.writingpractice.repository;

import com.example.writingpractice.model.WritingPracticeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WritingPracticeQuestionRepository extends JpaRepository<WritingPracticeQuestion, Long> {

    @Query(value = "SELECT * FROM writing_practice_question WHERE task_number = :taskNumber ORDER BY random() LIMIT 1", nativeQuery = true)
    WritingPracticeQuestion findRandomQuestionByTaskNumber(@Param("taskNumber") int taskNumber);
}
