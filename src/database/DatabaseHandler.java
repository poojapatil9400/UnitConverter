package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    // Use a relative path for the SQLite database
    private static final String DB_URL = "jdbc:sqlite:data/expenses.db";

    static {
        try {
            // Explicitly load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load SQLite JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to SQLite database!");
                // Create the expenses table if it doesn't exist
                String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "category TEXT NOT NULL, " +
                             "amount REAL NOT NULL, " +
                             "date TEXT NOT NULL)";
                conn.createStatement().execute(sql);
                System.out.println("Expenses table created or already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addExpense(String category, double amount, String date) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO expenses (category, amount, date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, category);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, date);
            preparedStatement.executeUpdate();
            System.out.println("Expense added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllExpenses() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM expenses";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");

                System.out.println(
                    "ID: " + id + ", " +
                    "Category: " + category + ", " +
                    "Amount: " + amount + ", " +
                    "Date: " + date
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}