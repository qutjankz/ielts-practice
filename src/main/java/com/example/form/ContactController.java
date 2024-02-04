package com.example.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody Contact contact) {
        try {
            // Задаем дату и время текущим моментом
            contact.setDate(LocalDateTime.now());

            // Сохраняем новый контакт в базе данных
            contactRepository.save(contact);

            return new ResponseEntity<>("Contact created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create contact", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}