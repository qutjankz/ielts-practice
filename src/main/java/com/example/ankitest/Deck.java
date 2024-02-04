//package com.example.ankitest;
//
//import java.io.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//// purpose: create multiple Card objects to add to this 1 Deck object (since each Deck object is set within the Deck class)
//public class Deck {
//    private final String fileName;
//    private final ArrayList<Card> cards = new ArrayList<>();
//    // constructor taking in fileName & setting it
//    public Deck(String fileName) {
//        this.fileName = fileName;
//    }
//
//    // save cards within Deck, done after all cards are written back into the file using PrintWriter writing to the .deck file
//    public void save() {
//        try {
//            File file = new File("decks/" + fileName);
//            PrintWriter out = new PrintWriter(new FileOutputStream(file));
//            for (int i = 0; i <= cards.size() - 1; i++) {
//                cards.get(i).save(out);
//            }
//            out.close();
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Deck not found!");
//        }
//
//    }
//
//    // add card to the cards ArrayList
//    public void addCard(Card card) {
//        cards.add(card);
//    }
//
//    // load & shuffle deck
//    public void loadDeck() {
//        // load deck (read out of .deck file)
//        try {
//            File file = new File("decks/" + fileName);
//            Scanner fIn = new Scanner(new FileInputStream(file));
//            while (fIn.hasNext()) {
//                String question = fIn.nextLine();
//                String answer = fIn.nextLine();
//                int daysBetween = Integer.parseInt(fIn.nextLine());
//                LocalDate dueDate = LocalDate.parse(fIn.nextLine());
//                boolean newCard = Boolean.parseBoolean(fIn.nextLine());
//                fIn.nextLine();
//                addCard(setCardObject(question, answer, daysBetween, dueDate, newCard));
//            }
//            fIn.close();
//        } catch (FileNotFoundException e) {
//            System.err.println("Error: " + fileName + " not found");
//        } catch (NoSuchElementException e) {
//            e.printStackTrace();
//            System.err.println("Error: file" + fileName + " has no more line elements to be read???");
//        }
//
//        // shuffle deck
//        Collections.shuffle(cards);
//    }
//
//    // study cards by reading .deck file, shuffling, then giving user questions the cards have (unless it already went through 10 new cards this run iteration)
//    public void study() {
//        // load the deck & shuffle first
//        loadDeck();
//
//        // give question & ask if ready
//        int z = 1;
//        // loops infinitely so wrong questions are repeated
//        while (true) {
//            boolean wrongQuestionExists = false;
//            // loops through shuffled cards
//            for (int i = 0; i <= cards.size() - 1; i++) {
//                if (cards.get(i).isDue()) {
//                    // new card = true & z <= 10
//                    if (cards.get(i).isNew() && z <= 10) {
//                        if (!cards.get(i).ask()) {
//                            System.out.println("Unfortunate, but it's ok! It's only meant to help you improve!");
//                            wrongQuestionExists = true;
//                        }
//                        z++;
//                        cards.get(i).setNewCardFalse();
//                    }
//                    // new card = false (z is irrelevant)
//                    else if (!cards.get(i).isNew()) {
//                        if (!cards.get(i).ask()) {
//                            System.out.println("Unfortunate, but it's ok! It's only meant to help you improve!");
//                            wrongQuestionExists = true;
//                        }
//                    }
//                }
//            }
//
//            // break loop if wrongQuestionExists is false after loop through all shuffled cards ArrayList
//            if (wrongQuestionExists == false) {
//                break;
//            }
//        }
//
//        // after looping is done print no more cards to study today
//        System.out.println("No more cards to study today in this deck, you're good! (" + fileName + ")");
//    }
//
//    // create a new Card object with tons of given parameters
//    public Card setCardObject(String question, String answer, int daysBetween, LocalDate dueDate, boolean newCard) {
//        return new Card(question, answer, daysBetween, dueDate, newCard);
//    }
//
//    // main method to check if adding cards to the deck & printing them out works
//    public static void main(String[] args) {
//        Deck deck = new Deck("r.deck");
//        Scanner n = new Scanner(System.in);
//        Card card;
//        for (int i = 0; i < 3; i++) {
//            card = new Card(n);
//            deck.addCard(card);
//        }
//        deck.save();
//    }
//}
