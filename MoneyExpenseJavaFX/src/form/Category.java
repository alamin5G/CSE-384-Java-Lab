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

public class Category extends Application {

    private TableView<CategoryEntry> tableView;
    private TextField categoryField;
    private ObservableList<CategoryEntry> categoryData;

    @Override
    public void start(Stage stage) {
        categoryData = FXCollections.observableArrayList();

        categoryField = new TextField();
        categoryField.setPromptText("Enter New Category");
        

        Button addCategoryButton = new Button("Add Category");
           addCategoryButton.setStyle("-fx-font-weight: bold; -fx-text-fill:#ffffff; -fx-background-color: green ");
        addCategoryButton.setOnAction(e -> addCategory());

        Button deleteCategoryButton = new Button("Delete Selected");
        deleteCategoryButton.setStyle("-fx-font-weight: bold; -fx-text-fill:#ffffff; -fx-background-color: red ");
        deleteCategoryButton.setOnAction(e -> deleteCategory());

        tableView = new TableView<>();
        setupTableView();

        VBox inputBox = new VBox(10,
                new HBox(10, new Label("Category:"), categoryField, addCategoryButton, deleteCategoryButton));
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(10, inputBox, tableView);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Manage Categories");
        stage.show();

        getCategories();
    }

    private void setupTableView() {
        TableColumn<CategoryEntry, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<CategoryEntry, String> nameColumn = new TableColumn<>("Category Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableView.getColumns().addAll(idColumn, nameColumn);
        tableView.setItems(categoryData);
    }

    private void getCategories() {
        try {
            categoryData.clear();
            ResultSet rs = DatabaseConnection.statement.executeQuery("SELECT * FROM category_info");
            int id = 0;

            while (rs.next()) {
                String name = rs.getString("category");
                categoryData.add(new CategoryEntry(++id, name));
            }

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch categories", ex.getMessage());
        }
    }

    private void addCategory() {
        String category = categoryField.getText().trim();

        if (category.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Category cannot be empty", null);
            return;
        }

        try {
            DatabaseConnection.statement.executeUpdate("INSERT INTO category_info (category) VALUES ('" + category + "')");
            showAlert(Alert.AlertType.INFORMATION, "Success", "Category added successfully", null);

            categoryField.clear();
            getCategories();
           

        } catch (SQLException ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to add category", ex.getMessage());
        }
    }

    private void deleteCategory() {
        CategoryEntry selectedCategory = tableView.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a category to delete.", null);
            return;
        }

        // Show confirmation dialog
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Delete");
        confirmationAlert.setHeaderText("Are you sure you want to delete the category?");
        confirmationAlert.setContentText("Category: " + selectedCategory.nameProperty().get());

        if (confirmationAlert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                String categoryName = selectedCategory.nameProperty().get();
                DatabaseConnection.statement.executeUpdate("DELETE FROM category_info WHERE category = '" + categoryName + "'");
                showAlert(Alert.AlertType.INFORMATION, "Success", "Category deleted successfully.", null);

                getCategories();

            } catch (SQLException ex) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to delete category", ex.getMessage());
            }
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

