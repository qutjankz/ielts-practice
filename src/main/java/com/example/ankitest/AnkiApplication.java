package com.example.ankitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.ankitest")
public class AnkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnkiApplication.class, args);
    }
}
