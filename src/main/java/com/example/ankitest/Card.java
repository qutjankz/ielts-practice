//package com.example.ankitest;
//
//import java.io.PrintWriter;
//import java.time.LocalDate;
//import java.util.Scanner;
//
//import static java.time.LocalDate.now;
//
//// Card class
//// sets Card objects & saves the cards created as objects from the Deck class
//public class Card {
//    private String question;
//    private String answer;
//    private int daysBetween;
//    private LocalDate dueDate = now();
//    private boolean newCard;
//    AnkiQuestion ankiQuestion;
//
//    // constructor taking in & setting question, answer, daysBetween, dueDate, & newCard
//    public Card(String question, String answer, int daysBetween, LocalDate dueDate, boolean newCard) {
//        this.question = question;
//        this.answer = answer;
//        this.daysBetween = daysBetween;
//        this.dueDate = dueDate;
//        this.newCard = newCard;
//    }
//
//    // constructor taking in & setting question & answer through given parameters
//    public Card(String question, String answer) {
//        this.question = question;
//        this.answer = answer;
//        newCard = true;
//    }
//
//    // set question & answer through Scanner
//    public Card(Scanner in) {
//        // new card info
//        newCard = true;
//        System.out.println("With this newly created card...");
//
//        // question to set
//        System.out.println("Question to set?");
//        question = in.nextLine();
//        if (question.equalsIgnoreCase("stop")) {
//            System.out.println("Going back to options...");
//            return;
//        }
//
//        // answer to set
//        System.out.println("Answer to set?");
//        answer = in.nextLine();
//        if (answer.equalsIgnoreCase("stop")) {
//            System.out.println("Going back to options...");
//            return;
//        }
//
//        System.out.println("Question & answer set for this card!");
//    }
//
//    // set newCard variable to false
//    public void setNewCardFalse() {
//        newCard = false;
//    }
//
//    // save Card question through PrintWriter
//    public void save(PrintWriter out) {
//        out.println(question);
//        out.println(answer);
//        out.println(daysBetween);
//        out.println(dueDate);
//        out.println(newCard);
//        out.println();
//    }
//
//    // check if dueDate is before today or is today
//    public boolean isDue() {
//        if (dueDate.isBefore(now()) || dueDate.isEqual(now())) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // check if the card is new or not
//    public boolean isNew() {
//        if (newCard == false) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    // check if user got question right or not & ask for difficulty, then do math based on difficulty if answer was not wrong
//    public boolean ask() {
//        // declare scanner
//        Scanner questionChecker = new Scanner(System.in);
//
//        // ask question
//        System.out.println("Here's the question: ");
//        System.out.println(ankiQuestion.getQuestion());
//        System.out.println("Ready for the answer? [ENTER] if so! \n(You can also guess your answer before pressing [ENTER] if you would like!)");
//
//        // search for enter press
//        String userReady = questionChecker.nextLine();
//        // until enter is detected
//        while (!(userReady.contains(""))) {
//            userReady = questionChecker.nextLine();
//        }
//        System.out.println("Answer:");
//        System.out.println(ankiQuestion.getAnswer());
//
//        // set question's difficulty & if user got it right or wrong
//        beginning:
//        while (true) {
//            System.out.println("How was that question? Options:");
//            System.out.println("\"(w)rong\", \"(d)ifficult\", \"(c)orrect\", & \"(e)asy\"");
//            String userChoice;
//            try {
//                userChoice = questionChecker.nextLine().substring(0, 1);
//            } catch (StringIndexOutOfBoundsException e) {
//                // ask user for input again from beginning of loop
//                continue beginning;
//            }
//            if (userChoice.equalsIgnoreCase("w")) {
//                dueDate = now();
//                daysBetween = 0;
//                System.out.println("This card will next be due in " + daysBetween + " days (today)");
//                System.out.println("aka on or after " + dueDate + " (YYYY-MM-DD)");
//                System.out.println();
//                return false;
//            } else if (userChoice.equalsIgnoreCase("d")) {
//                daysBetween = (int) Math.round((daysBetween + 1) * 1.1);
//                dueDate = dueDate.plusDays(daysBetween);
//                break;
//            } else if (userChoice.equalsIgnoreCase("c")) {
//                daysBetween = (int) Math.round((daysBetween + 1) * 1.25);
//                dueDate = dueDate.plusDays(daysBetween);
//                break;
//            } else if (userChoice.equalsIgnoreCase("e")) {
//                daysBetween = (int) Math.round((daysBetween + 1) * 1.5);
//                dueDate = dueDate.plusDays(daysBetween);
//                break;
//            } else {
//                System.out.println("Invalid option!\nPlease input \"w\", \"d\", \"c\", \"e\" only!");
//                // give hint if user input wasn't empty
//                if (!userReady.equals("")) {
//                    System.out.println("Your guess was \"" + userReady + "\" before to the question, if that helps!");
//                }
//                System.out.println();
//            }
//        }
//
//        // give info when card will be due
//        if (daysBetween == 1) {
//            System.out.println("This card will next be due in " + daysBetween + " day");
//        } else {
//            System.out.println("This card will next be due in " + daysBetween + " days");
//        }
//        System.out.println("aka on or after " + dueDate + " (YYYY-MM-DD)");
//        System.out.println();
//        return true;
//    }
//
//    // main method to test if Card(Scanner in) works
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        Card card = new Card(in);
//        card.ask();
//    }
//}
