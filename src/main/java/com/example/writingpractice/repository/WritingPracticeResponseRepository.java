// src/main/java/com/example/writingpractice/repository/WritingPracticeResponseRepository.java

package com.example.writingpractice.repository;

import com.example.writingpractice.model.WritingPracticeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingPracticeResponseRepository extends JpaRepository<WritingPracticeResponse, Long> {
}
