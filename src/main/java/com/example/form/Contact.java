package com.example.form;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Table(name = "contact")
@Entity
@Getter
@Setter

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String city;
    private LocalDateTime modified;
    private LocalDateTime date;

    // Конструкторы, геттеры, сеттеры и другие методы


    // геттеры, сеттеры и другие методы
}
