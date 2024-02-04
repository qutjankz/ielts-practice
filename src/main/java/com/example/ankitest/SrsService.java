package com.example.ankitest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SrsService {

    private int currentIndex = 0;
    @Autowired
    private AnkiRepository questionRepository;
//    public void processTodayQuestions() {
//        List<AnkiQuestion> todayQuestions = getTodayQuestions();
//
//        if (!todayQuestions.isEmpty()) {
//            System.out.println("Today's questions:");
//            for (AnkiQuestion question : todayQuestions) {
//                processSingleQuestion(question);
//            }
//        } else {
//            System.out.println("No questions for today.");
//        }
//    }

    private List<AnkiQuestion> getTodayQuestions() {
        LocalDate today = LocalDate.now();
        return questionRepository.findByDueDate(today);
    }

    public void processTodayQuestions() {
        List<AnkiQuestion> todayQuestions = getTodayQuestions();

        if (!todayQuestions.isEmpty()) {
            System.out.println("Today's questions:");

            // Проходим по всем вопросам по порядку
            for (AnkiQuestion question : todayQuestions) {
                processSingleQuestion(question);
            }
        } else {
            System.out.println("No questions for today.");
        }
    }

    private void processSingleQuestion(AnkiQuestion ankiQuestion) {
        System.out.println("Here's the question: ");
        System.out.println(ankiQuestion.getQuestion());
        System.out.println("Ready for the answer? [ENTER] if so!");

        Scanner questionChecker = new Scanner(System.in);

        System.out.println("Answer:");
        System.out.println(ankiQuestion.getAnswer());

        String userChoice;
        System.out.println("How was that question? Options:");
        System.out.println("\"(w)rong\", \"(d)ifficult\", \"(c)orrect\", & \"(e)asy\"");
        try {
            userChoice = questionChecker.nextLine().substring(0, 1);
        } catch (StringIndexOutOfBoundsException e) {
            userChoice = "w"; // Assume wrong if no valid input
        }


        updateSrsData(ankiQuestion, userChoice);
    }

    private List<AnkiQuestion> getRemainingQuestions() {
        List<AnkiQuestion> allQuestions = (List<AnkiQuestion>) questionRepository.findAll();
        List<AnkiQuestion> remainingQuestions = new ArrayList<>();

        // Проходим по всем вопросам и добавляем в список те, у которых не изменились даты
        for (AnkiQuestion question : allQuestions) {
            if (question.getDueDate().isEqual(LocalDate.now())) {
                remainingQuestions.add(question);
            }
        }

        return remainingQuestions;
    }

    public boolean ask() {
        processTodayQuestions();
        Scanner questionChecker = new Scanner(System.in);

        boolean allQuestionsAnswered = false;

        while (!allQuestionsAnswered) {
            List<AnkiQuestion> remainingQuestions = getRemainingQuestions();
            allQuestionsAnswered = true; // Предполагаем, что все вопросы уже обработаны

            Iterator<AnkiQuestion> iterator = remainingQuestions.iterator();
            while (iterator.hasNext()) {
                AnkiQuestion ankiQuestion = iterator.next();

                if (!ankiQuestion.getDueDate().isEqual(LocalDate.now())) {
                    // Если у вопроса дата поменялась, пропускаем его
                    continue;
                }

                allQuestionsAnswered = false; // Есть еще вопросы для обработки

                System.out.println("Here's the question: ");
                System.out.println(ankiQuestion.getQuestion());

                System.out.println("Answer:");
                System.out.println(ankiQuestion.getAnswer());

                while (true) {
                    String userChoice;
                    System.out.println("How was that question? Options:");
                    System.out.println("\"(w)rong\", \"(d)ifficult\", \"(c)orrect\", & \"(e)asy\"");
                    try {
                        userChoice = questionChecker.nextLine().substring(0, 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        continue;
                    }

                    updateSrsData(ankiQuestion, userChoice);

                    if (userChoice.equalsIgnoreCase("w") || userChoice.equalsIgnoreCase("d")) {
                        iterator.remove(); // Убираем вопрос из списка, чтобы не повторять
                        break; // Прерывание цикла, если пользователь выбрал "wrong" или "difficult"
                    } else {
                        break; // Прерывание цикла, если пользователь выбрал "correct" или "easy"
                    }
                }
            }
        }

        return true; // Возвращаем true, если все вопросы обработаны успешно
    }


    public AnkiQuestion getNextQuestion() {
        List<AnkiQuestion> remainingQuestions = getRemainingQuestions();
        if (!remainingQuestions.isEmpty()) {
            return remainingQuestions.get(0);
        } else {
            return null; // Вопросов нет
        }
    }

    public boolean updateSrsData(Long id, String userChoice) {
        Optional<AnkiQuestion> ankiQuestionOptional = questionRepository.findById(id);

        if (ankiQuestionOptional.isPresent()) {
            AnkiQuestion ankiQuestion = ankiQuestionOptional.get();
            updateSrsData(ankiQuestion, userChoice);
            return true;
        } else {
            return false; // Вопрос с указанным ID не найден
        }
    }

    private void updateSrsData(AnkiQuestion ankiQuestion, String userChoice) {
        int daysBetween = ankiQuestion.getDaysBetween();

        switch (userChoice.toLowerCase()) {
            case "w":
                daysBetween = 0;
                break;
            case "d":
                daysBetween = (int) Math.round((daysBetween + 1) * 1.1);
                break;
            case "c":
                daysBetween = (int) Math.round((daysBetween + 1) * 1.25);
                break;
            case "e":
                daysBetween = (int) Math.round((daysBetween + 1) * 1.5);
                break;
            default:
                throw new IllegalArgumentException("Invalid user choice: " + userChoice);
        }

        // В любом случае обновляем дату, даже при выборе "correct" или "easy"
        ankiQuestion.setDaysBetween(daysBetween);
        ankiQuestion.setDueDate(LocalDate.now().plusDays(daysBetween));

        questionRepository.save(ankiQuestion);
    }
}

