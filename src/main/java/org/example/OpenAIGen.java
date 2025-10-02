/*
* Author: Raymond Ynoa
* */

package org.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Use OpenAI's GPT to generate sample data (10 books).
public class OpenAIGen {

    // OpenAI API key. Copy and paste your API key here.
    private static final String OPENAI_API_KEY = "";

    // MySQL Workbench database connection details. Update if necessary.
    private static final String DB_URL = "";  // Database Host URL
    private static final String DB_USER = "";  // Database Username
    private static final String DB_PASSWORD = "";  // Database Password

    public static void main(String[] args) {
        try {
            // Generate 10 sample books using OpenAI API.
            String generatedBooks = generateBooks();

            // Insert the generated books into MySQL database.
            insertBooksIntoDatabase(generatedBooks);

            System.out.println("Sample books inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate data for database using OpenAI API.
    private static String generateBooks() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Create the prompt for OpenAI API.
        String prompt = "Generate 10 book titles, authors, publication years, and genres in the following format: title, author, year, genre. Without using a numbered list or parentheses around the titles.";

        // Prepare the request payload for the GPT endpoint.
        String jsonPayload = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [{\n" +
                "    \"role\": \"system\",\n" +
                "    \"content\": \"You are a helpful assistant.\"\n" +
                "  }, {\n" +
                "    \"role\": \"user\",\n" +
                "    \"content\": \"" + prompt + "\"\n" +
                "  }]\n" +
                "}";

        // Prepare the request.
        RequestBody body = RequestBody.create(
                jsonPayload,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        // Get response JSON and extract the generated data.
        JsonNode responseJson = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.body().string());
        return responseJson.get("choices").get(0).get("message").get("content").asText().trim();
    }

    // Method to insert the generated books into the books table.
    private static void insertBooksIntoDatabase(String generatedBooks) {
        // Use java.sql.Connection explicitly
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String[] books = generatedBooks.split("\n");
            String sql = "INSERT INTO books (title, author, year, genre) VALUES (?, ?, ?, ?)";

            // Prepare statement for inserting books into the database
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (String book : books) {
                    String[] bookDetails = book.split(",");
                    if (bookDetails.length == 4) {  // If length is equals 4 expected values: title, author, year, genre.
                        // Capture values.
                        String title = bookDetails[0].trim();
                        String author = bookDetails[1].trim();
                        int year = Integer.parseInt(bookDetails[2].trim());
                        String genre = bookDetails[3].trim();

                        // Set values in the preparedStatement.
                        preparedStatement.setString(1, title);
                        preparedStatement.setString(2, author);
                        preparedStatement.setInt(3, year);
                        preparedStatement.setString(4, genre);

                        // Execute insert.
                        preparedStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
