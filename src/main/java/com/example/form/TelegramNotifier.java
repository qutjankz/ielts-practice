package com.example.form;

import jakarta.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelegramNotifier {

    private static final String CHAT_ID = "-971777015";
    private static final String TOKEN = "6714259916:AAGKDwOXRxM1LJDLDLUdpxQ4dWWfBxC7p8g";

    private Long lastProcessedId = 0L;

    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(TelegramNotifier.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        // Запуск проверки каждые 10 секунд
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::checkForNewData, 0, 10, TimeUnit.SECONDS);

        return args -> {
            // Ничего не делаем при старте
        };
    }

    private void checkForNewData() {
        try {
            // Пример использования Spring Data JPA
            Long maxId = contactRepository.findMaxId();

            // Проверяем, изменилось ли максимальное значение id
            if (!maxId.equals(lastProcessedId)) {
                // Если изменилось, получаем данные и отправляем сообщение
                String message = fetchDataFromDatabase(maxId);
                sendMessageToChat(message);

                // Обновляем последнее обработанное значение id
                lastProcessedId = maxId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String fetchDataFromDatabase(Long maxId) {
        try {
            // Пример использования Spring Data JPA
            Contact contact = contactRepository.findById(maxId).orElse(null);
            if (contact != null) {
                return "Name: " + contact.getName() + "\n" + "Number: "  + contact.getPhoneNumber() + "\n" + "Date: " + contact.getDate() + "\n" + "City: " + contact.getCity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No data available";
    }

    private void sendMessageToChat(String message) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .version(HttpClient.Version.HTTP_2)
                    .build();

            UriBuilder builder = UriBuilder
                    .fromUri("https://api.telegram.org")
                    .path("/{token}/sendMessage")
                    .queryParam("chat_id", CHAT_ID)
                    .queryParam("text", message);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(builder.build("bot" + TOKEN))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
