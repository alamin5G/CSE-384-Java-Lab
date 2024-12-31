package form;

import db.DatabaseConnection;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import javafx.scene.text.Font;

public class SpendingTracker extends Application {

    private TableView<SpendingEntry> tableView;
    private ComboBox<String> categoryComboBox;
    private TextField amountField;
    private DatePicker datePicker;
    private Label totalLabel;
    private ObservableList<SpendingEntry> spendingData;

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }

    @Override
    public void start(Stage stage) {
        spendingData = FXCollections.observableArrayList();

        // UI Components
        datePicker = new DatePicker();
        categoryComboBox = new ComboBox<>();
        categoryComboBox.setPromptText("Select Category");
        amountField = new TextField();
        amountField.setPromptText("Enter Amount");
        
         totalLabel = new Label("Total: 0");
        totalLabel.setFont(Font.font("Arial", 18));
        totalLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2E8B57;");

        Button addCategoryButton = new Button("Add New Category");
        addCategoryButton.setOnAction(e -> openCategory());

        Button viewSpendingButton = new Button("View All Expenses");
        viewSpendingButton.setOnAction(e -> openViewSpending());

        Button addExpenseButton = new Button("Submit");
        addExpenseButton.setOnAction(e -> addExpense());

        Button deleteExpenseButton = new Button("Delete");
        deleteExpenseButton.setOnAction(e -> deleteExpense());

        tableView = new TableView<>();
        setupTableView();

        // Layouts
        HBox inputBox = new HBox(10, new Label("Date:"), datePicker, new Label("Amount:"), amountField,
                new Label("Category:"), categoryComboBox, addExpenseButton);
        inputBox.setPadding(new Insets(10));

        HBox actionBox = new HBox(10, addCategoryButton, viewSpendingButton, deleteExpenseButton, totalLabel);
        actionBox.setPadding(new Insets(10));

        VBox root = new VBox(10, inputBox, actionBox, tableView);
        root.setPadding(new Insets(20));

        // Scene and Stage setup
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("MoneyExpense - Spending Tracker");
        stage.show();

        // Load initial data
        loadCategories();
        getEntries();
    }

    private void setupTableView() {
        TableColumn<SpendingEntry, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<SpendingEntry, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        TableColumn<SpendingEntry, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        TableColumn<SpendingEntry, Integer> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        tableView.getColumns().addAll(idColumn, dateColumn, categoryColumn, amountColumn);
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

    private void getEntries() {
        try {
            spendingData.clear();
            LocalDate currentDate = LocalDate.now();
            LocalDate pastDate = currentDate.minusDays(31);

            ResultSet rs = DatabaseConnection.statement.executeQuery(
                    "SELECT * FROM spendings WHERE sdate <= '" + currentDate + "' AND sdate >= '" + pastDate + "'");
            int total = 0;

            while (rs.next()) {
                int id = rs.getInt("sid");
                String date = rs.getDate("sdate").toString();
                String category = rs.getString("category");
                int amount = rs.getInt("amount");

                spendingData.add(new SpendingEntry(id, date, category, amount));
                total += amount;
            }

            totalLabel.setText("Total: " + total);

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch data", ex.getMessage());
        }
    }

    private void addExpense() {
        LocalDate date = datePicker.getValue();
        String amountText = amountField.getText();
        String category = categoryComboBox.getValue();

        if (date == null || amountText.isEmpty() || category == null || category.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Please fill in all fields", null);
            return;
        }

        try {
            int amount = Integer.parseInt(amountText);

            DatabaseConnection.statement.executeUpdate(
                    "INSERT INTO spendings (category, sdate, amount) VALUES ('"
                    + category + "', '" + date + "', " + amount + ")");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Expense added successfully", null);

            getEntries();

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add expense", ex.getMessage());
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Amount must be a number", null);
        }
    }

    private void deleteExpense() {
        SpendingEntry selected = tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select an expense to delete", null);
            return;
        }

        // Show confirmation dialog
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Delete");
        confirmationAlert.setHeaderText("Are you sure you want to delete the Expense?");
        confirmationAlert.setContentText("Expense Deleted from : " + selected.categoryProperty().get());

        if (confirmationAlert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                DatabaseConnection.statement.executeUpdate("DELETE FROM spendings WHERE sid = " + selected.getId());
                showAlert(Alert.AlertType.INFORMATION, "Success", "Expense deleted successfully", null);

                getEntries();

            } catch (SQLException ex) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete expense", ex.getMessage());
            }
        }

    }

    private void openCategory() {
        Category category = new Category();
        try {
            category.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openViewSpending() {
        ViewSpending viewSpending = new ViewSpending();
        try {
            viewSpending.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
