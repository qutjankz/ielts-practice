// src/main/java/com/example/writingpractice/model/WritingPracticeResponse.java

package com.example.writingpractice.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "writing_practice_response")
@Getter
@Setter
public class WritingPracticeResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "answer_text", nullable = false, columnDefinition = "TEXT")
    private String answerText;
}
