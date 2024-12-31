package form;

import db.DatabaseConnection;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

public class ViewSpending extends Application {

    private DatePicker fromDatePicker, toDatePicker;
    private TableView<SpendingEntry> tableView;
    private Label totalLabel;
    private ComboBox<String> categoryComboBox;
    private ObservableList<SpendingEntry> spendingData;

    @Override
    public void start(Stage stage) {
        spendingData = FXCollections.observableArrayList();

        // UI Components
        fromDatePicker = new DatePicker();
        toDatePicker = new DatePicker();
        totalLabel = new Label("Total: 0");
        totalLabel.setFont(Font.font("Arial", 18));
        totalLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        categoryComboBox = new ComboBox<>();
        categoryComboBox.setPromptText("Select Category");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> performSearch());

        tableView = new TableView<>();
        setupTableView();

        // Layouts
        VBox dateRangeBox = new VBox(10, 
                new HBox(10, new Label("From:"), fromDatePicker),
                new HBox(10, new Label("To:"), toDatePicker)
        );

        VBox categoryBox = new VBox(10, new HBox(10, new Label("Category:"), categoryComboBox));

        HBox controlsBox = new HBox(20, dateRangeBox, categoryBox, searchButton);
        controlsBox.setAlignment(Pos.CENTER);
        controlsBox.setPadding(new Insets(20));

        VBox totalBox = new VBox(10, totalLabel);
        totalBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, controlsBox, tableView, totalBox);
        root.setPadding(new Insets(20));

        // Scene setup
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("View Spending");
        stage.show();

        // Load initial data
        loadCategories();
    }

    private void setupTableView() {
        TableColumn<SpendingEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        TableColumn<SpendingEntry, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        TableColumn<SpendingEntry, Integer> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        tableView.getColumns().addAll(dateColumn, categoryColumn, amountColumn);
        tableView.setItems(spendingData);
    }

    private void loadCategories() {
        try {
            ResultSet rs = DatabaseConnection.statement.executeQuery("SELECT * FROM category_info");
            categoryComboBox.getItems().clear();
            while (rs.next()) {
                categoryComboBox.getItems().add(rs.getString("category"));
            }
        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load categories", ex.getMessage());
        }
    }

    private void performSearch() {
        LocalDate from = fromDatePicker.getValue();
        LocalDate to = toDatePicker.getValue();
        String category = categoryComboBox.getValue();

        String query = "SELECT * FROM spendings WHERE 1=1";
        boolean hasDateFilter = false;

        if (from != null && to != null) {
            query += " AND sdate >= '" + from + "' AND sdate <= '" + to + "'";
            hasDateFilter = true;
        }

        if (category != null && !category.isEmpty()) {
            query += " AND category = '" + category + "'";
        }

        query += " ORDER BY sdate ASC";

        try {
            spendingData.clear();
            ResultSet rs = DatabaseConnection.statement.executeQuery(query);
            int total = 0;

            while (rs.next()) {
                String date = rs.getDate("sdate").toString();
                String resultCategory = rs.getString("category");
                int amount = rs.getInt("amount");

                spendingData.add(new SpendingEntry(date, resultCategory, amount));
                total += amount;
            }

            tableView.setItems(spendingData);
            totalLabel.setText("Total: " + total);

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch data", ex.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
