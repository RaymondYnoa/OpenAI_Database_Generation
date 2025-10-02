/*
 * Author: Raymond Ynoa
 * */

package org.example;

// Test CRUD operations from DatabaseUtil class.
public class App {
    public static void main(String[] args) {
        // Create a new book.
        System.out.println("Testing Create...");
        DatabaseUtil.createBook(2,"The Duckling", "Hans Andersen", 1943, "historic");

        // Read all books.

        System.out.println("\nTesting Read...");
        System.out.println("Books in the database:");
        DatabaseUtil.readBooks();

        // Update a book.
        System.out.println("\nTesting Update...");
        DatabaseUtil.updateBook(2, "The Ugly Duckling", "Hans Christian Andersen", 1843, "Fairy Tale");
        DatabaseUtil.readBooks();

        // Delete a book.
        System.out.println("\nTesting Delete...");
        DatabaseUtil.deleteBook(2);

        // Verify deletion.
        System.out.println("\nAfter deletion:");
        DatabaseUtil.readBooks();
    }
}
