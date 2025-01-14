/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package calculatorapp;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Labels
        Label num1Label = new Label("Number 1:");
        Label num2Label = new Label("Number 2:");
        Label resultLabel = new Label("Result:");

        // Text Fields
        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false); // Result field is read-only

        // Buttons
        Button addButton = new Button("Add");
        Button subtractButton = new Button("Subtract");

        // Event Handlers
        addButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = num1 + num2;
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid Input");
            }
        });

        subtractButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = num1 - num2;
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultField.setText("Invalid Input");
            }
        });

        // Layout
        VBox vBox = new VBox(10, num1Label, num2Label, resultLabel);
        VBox vBoxField = new VBox(10, num1Field, num2Field, resultField);
        HBox hBox = new HBox(10, vBox, vBoxField, new HBox(10, addButton, subtractButton));
        hBox.setAlignment(Pos.CENTER);
        
        
       

        // Scene and Stage
        Scene scene = new Scene(hBox, 500, 400);
        primaryStage.setTitle("JavaFX Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
