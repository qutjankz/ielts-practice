package com.example.ankitest;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnkiRepository extends CrudRepository<AnkiQuestion, Long> {

    List<AnkiQuestion> findByDueDate(LocalDate today);
}
