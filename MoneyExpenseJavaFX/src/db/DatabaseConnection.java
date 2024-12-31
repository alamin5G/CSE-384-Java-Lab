package db;

import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnection {

    public static Connection connection;
    public static Statement statement;

    static {
        try {
            // Host location with database name, username, and password
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spendingTracker", "root", "252646");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            // Display an error message in an Alert dialog
            showAlert("Database Connection Error", "Could not connect to the database.", ex.getMessage());
        }
    }

    private static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
