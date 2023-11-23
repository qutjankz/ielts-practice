// src/main/java/com/example/writingpractice/model/WritingPracticeQuestion.java

package com.example.writingpractice.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "writing_practice_question")
@Getter
@Setter
public class WritingPracticeQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "picture_url")
    private String pictureUrl;
}
