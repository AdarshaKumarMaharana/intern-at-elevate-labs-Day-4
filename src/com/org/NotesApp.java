package com.org;

import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("==== Welcome to Notes App ====");

        do {
            System.out.println("\n1. Write a Note");
            System.out.println("2. Read Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next(); // consume invalid input
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeNote(scanner);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void writeNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(note);
            System.out.println("Note saved successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readNotes() {
        System.out.println("\n==== Your Notes ====");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }

            if (empty) {
                System.out.println("No notes found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes yet. Start writing!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
