//package com.example.ankitest;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import static java.nio.file.Files.exists;
//
//// purpose: create multiple Deck objects by searching through the directory of decks
//public class Main {
//
//    // get decks from decks directory (https://docs.oracle.com/javase/tutorial/essential/io/dirs.html#listdir)
//    public static ArrayList<String> getDecks() {
//        ArrayList<String> decks = new ArrayList<>();
//        Path dir = Paths.get("decks");
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{deck}")) { // if wanted, could also add , java, txt, ml, etc. within {}
//            for (Path entry: stream) {
//                decks.add(entry.getFileName().toString());
//            }
//        } catch (IOException x) {
//            // IOException can never be thrown by the iteration.
//            // In this snippet, it can // only be thrown by newDirectoryStream.
//            System.err.println(x);
//        }
//        return decks;
//    }
//
//    // ask user to choose a deck
//    public static Deck chooseDeck() {
//
//        Scanner userDeckChoiceScanner = new Scanner(System.in);
//        int userDeckChoice = 0;
//
//        while (userDeckChoice == 0) {
//            System.out.println("What deck would you like to choose?");
//            for (int i = 0; i <= getDecks().size() - 1; i++) {
//                System.out.println(i+1 + ". " + getDecks().get(i));
//            }
//            userDeckChoice = userDeckChoiceScanner.nextInt();
//        }
//        Deck deck = new Deck(getDecks().get(userDeckChoice-1));
//        return deck;
//    }
//
//    // check if .deck files exist or not within the directory
//    public static boolean checkIfDeckFilesExists() {
//        // create decks directory if it doesn't exist
//        createDirectory();
//        if (getDecks().size() == 0) {
//            System.err.println("There are no decks created! Please create one before adding or studying a deck of cards!");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    // make user study the deck chosen
//    public static void study() {
//        Deck deck;
//
//        // asks user for what deck to choose if there is decks to choose from
//        if (checkIfDeckFilesExists() == true) {
//            System.out.println("Please choose from one of the decks available:");
//            deck = chooseDeck();
//        } else {
//            return;
//        }
//
//        // print 100 blank lines
//        for (int i = 0; i <= 100; i++) {
//            System.out.println();
//        }
//
//        // call Deck study method for instructions
//        deck.study();
//
//        // save deck
//        deck.save();
//    }
//
//    // add cards from user chosen Deck
//    public static void addCards() {
//        Deck deck;
//
//        // asks user for what deck to choose if there is decks to choose from
//        if (checkIfDeckFilesExists() == true) {
//            System.out.println("Please choose from one of the decks available:");
//            deck = chooseDeck();
//        } else {
//            return;
//        }
//
//        // ask for questions & answers in deck until "stop" is said by user
//        while (true) {
//            // ask for questions & answers
//            System.out.println("With this card about to be created...");
//
//            System.out.println("Question to set? (\"stop\" to stop)");
//            Scanner cardAdderScanner = new Scanner(System.in);
//            String question = cardAdderScanner.nextLine();
//            if (question.equalsIgnoreCase("stop")) {
//                deck.save();
//                System.out.println("Deck saved!");
//                System.out.println("Going back to options...");
//                break;
//            }
//
//            System.out.println("Answer to set? (\"stop\" to stop)");
//            String answer = cardAdderScanner.nextLine();
//            if (answer.equalsIgnoreCase("stop")) {
//                deck.save();
//                System.out.println("Deck saved!");
//                System.out.println("Going back to options...");
//                break;
//            }
//
//            Card card = new Card(question, answer);
//            System.out.println("Question & answer set for this card!");
//            deck.addCard(card);
//            System.out.println("Card added to deck!");
//        }
//    }
//
//    // create new directory (https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html)
//    // getProperty part (not needed anymore for path): https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
//    public static void createDirectory() {
//        Path path = Paths.get("");
//        try {
//            path = Paths.get("decks");
//            // create directory only if it exists
//            if (!(exists(path))) {
//                Files.createDirectory(path);
//            }
//        } catch (IOException e) {
//            // decks directory already exists
//            System.err.println("Path " + path + " not found?");
//        }
//    }
//
//    public static boolean operatingSystemCheck(String fileName) {
//        // search for invalid file characters in Windows & Linux (https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
//        String operatingSystem = System.getProperty("os.name").toUpperCase();
//
//        // check if the file is valid or not via characters inputted
//
//        // Windows (https://learn.microsoft.com/en-us/windows/win32/fileio/naming-a-file#file-and-directory-names)
//        if (operatingSystem.contains("WINDOWS")) {
//
//            // search for file equaling reserved names for the name of a file
//            String[] invalidEqualCharacters = {"CON", "PRN", "AUX", "NUL", "COM0", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT0", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};
//            for (int i = 0; i <= invalidEqualCharacters.length - 1; i++) {
//                if (fileName.equalsIgnoreCase(invalidEqualCharacters[i].concat(".deck"))) {
//                    System.err.println("Error: File name contains one of the reserved names in Windows! (" + invalidEqualCharacters[i] + " found!)");
//                    return false;
//                }
//            }
//            // search for file containing certain characters
//            String[] invalidContainCharacters = {"<", ">", ":", "\"", "/", "\\", "|", "?", "*"};
//            for (int i = 0; i <= invalidContainCharacters.length - 1; i++) {
//                if (fileName.contains(invalidContainCharacters[i])) {
//                    System.err.println("Error: File name contains an invalid character in Windows! (" + invalidContainCharacters[i] + " found!)");
//                    return false;
//                }
//            }
//        }
//
//        // Unix / Linux (https://dwheeler.com/essays/fixing-unix-linux-filenames.html - "The admin must not include 0x00 and 0x2F (“/") and would usually would not include 0x2E (“.")
//        else if (operatingSystem.contains("UNIX") || operatingSystem.contains("LINUX")) {
//            if (fileName.contains("\000") || fileName.contains("/")) {
//                System.err.println("Error: File name contains an invalid character in Unix or Linux!");
//                return false;
//            }
//        }
//
//        // Mac (https://en.wikipedia.org/wiki/Filename - macOS HFS+ mentions ":" and "/")
//        else if (operatingSystem.contains("MAC")) {
//            if (fileName.contains("/") || fileName.contains(":")) {
//                System.err.println("Error: File name contains an invalid character in Mac!");
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // create new deck
//    public static void createDeck() {
//        // create directory if there is no decks directory
//        createDirectory();
//
//        // declare scanner for user input
//        Scanner fileNameScanner = new Scanner(System.in);
//
//        // declare variables
//        boolean validCharFile = false;
//        boolean validNonDuplicateFile = false;
//        boolean validFilePossTrue = true;
//        String fileName = "";
//
//        // until file input characters from user is proven to be valid, keep looping until user inputs valid file name
//        while (!validNonDuplicateFile) {
//            while (!validCharFile) {
//
//                // ask user for input
//                System.out.println("Enter a valid deck name that does not already exist & does not contain any periods, unless you want to add \".deck\" to the end of your deck name!");
//                // set scanner for user input
//                fileName = fileNameScanner.nextLine();
//
//                // search carefully if it contains any "." besides ".deck" or not
//                while (fileName.contains(".") && !(fileName.endsWith(".deck"))) {
//                    System.out.println("Sorry, your deck can't be any file type you want, only \".deck!\" (no periods besides ending the file name with that!)");
//                    System.out.println("Please enter a valid deck name that does not already exist that doesn't contain any other file type besides \".deck\"!");
//                    fileName = fileNameScanner.nextLine();
//                }
//
//                // get path of .deck file requested by user
//                // first checking if user inputted ".deck" or not after their fileName
//                if (!fileName.endsWith(".deck")) {
//                    fileName = fileName.concat(".deck");
//                }
//
//                // change spaces to underscores
//                if (fileName.contains(" ")) {
//                    fileName = fileName.replace(" ", "_");
//                }
//
//                // if fileName was nothing (only enter was pressed)
//                if (fileName.equals("")) {
//                    System.err.println("Error: File name is empty!");
//                    validFilePossTrue = false;
//                }
//
//                // operating system check on fileName characters
//                if (operatingSystemCheck(fileName) == false) {
//                    validFilePossTrue = false;
//                }
//
//                // escape 1st while loop all tests come to true
//                if (validFilePossTrue) {
//                    validCharFile = true;
//                }
//
//                // set validFilePossTrue to true again for next user input, but don't escape while loop
//                validFilePossTrue = true;
//            }
//
//            // after that, get the path of the file (https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
//            // then by creating the path in a new File object
//            File file = new File("decks/" + fileName);
//
//            // search if file already exists
//            try {
//                if (file.exists()) {
//                    throw new IOException();
//                }
//                // create the file
//                Files.createFile(file.toPath());
//                System.out.println("File " + fileName + " successfully created!");
//                // escape 2nd loop
//                validNonDuplicateFile = true;
//
//            // unless it already exists
//            } catch (IOException e) {
//                System.err.println("Deck " + fileName + " already exists! (possibly, unless you're on an operating system outside of Windows/Linux/Unix/Mac, then an invalid character may have been inputted through that operating system)");
//                // go back through 1st loop
//                validCharFile = false;
//            }
//        }
//    }
//
//    // main method giving user options to study a deck of cards, add cards, create a new deck, or exit the tool
//    public static void main(String[] args) {
//        while (true) {
//            int userChoice;
//            System.out.println("Options:");
//            System.out.println("1. Study a deck of cards");
//            System.out.println("2. Add cards to a deck of cards");
//            System.out.println("3. Create a new deck of cards");
//            System.out.println("4. Exit the tool");
//            Scanner userOptionChoiceScanner = new Scanner(System.in);
//            userChoice = userOptionChoiceScanner.nextInt();
//            switch (userChoice) {
//                // good
//                case 1:
//                    study();
//                    break;
//                // good
//                case 2:
//                    addCards();
//                    break;
//                // good
//                case 3:
//                    createDeck();
//                    break;
//                // good
//                case 4:
//                    System.exit(0);
//            }
//        }
//    }
//}