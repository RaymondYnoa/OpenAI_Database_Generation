/*
 * Author: Raymond Ynoa
 * */

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Test connection to MySQL database and make CRUD operations methods accessible.
public class DatabaseUtil {

    // MySQL Workbench database connection details. Update if necessary.
    private static final String DB_URL = ""; // Database Host URL
    private static final String DB_USER = ""; // Database Username
    private static final String DB_PASSWORD = ""; // Database Password

    // Method to establish a connection to the MySQL database.
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Return the connection to the database using details.
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Print any errors that occur during the connection process.
            e.printStackTrace();
            throw new SQLException("Unable to connect to database", e);
        }
    }

    // CRUD (Create, Read, Update, Delete)
    // Create Operation
    public static void createBook(int id, String title, String author, int year, String genre) {
        String sql = "INSERT INTO books (id, title, author, year, genre) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, year);
            preparedStatement.setString(5, genre);

            preparedStatement.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Operation
    public static void readBooks() {
        String sql = "SELECT * FROM books";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int publicationYear = resultSet.getInt("year");
                String genre = resultSet.getString("genre");

                System.out.printf("ID: %d, Title: %s, Author: %s, Year: %d, Genre: %s%n",
                        id, title, author, publicationYear, genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Operation
    public static void updateBook(int id, String title, String author, int year, String genre) {
        String sql = "UPDATE books SET title = ?, author = ?, year = ?, genre = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Operation
    public static void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("The book was deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test the connection.
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connection to the database successful!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
