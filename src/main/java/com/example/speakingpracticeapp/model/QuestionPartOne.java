package com.example.speakingpracticeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question_part_one")
@Getter
@Setter
public class QuestionPartOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;

    private String question;

}
