package com.example.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Дополнительные методы могут быть добавлены, если необходимо

    @Query("SELECT MAX(c.id) FROM Contact c")
    Long findMaxId();
}
