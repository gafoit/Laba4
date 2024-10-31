package org.example.laba4;

import javafx.application.Application;
import javafx.geometry.Insets;
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

    private TextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage primaryStage) {
        display = new TextField();
        display.setEditable(false);
        display.setPrefSize(200, 50);
        display.setStyle("-fx-font-size: 18;");

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setAlignment(Pos.CENTER);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int row = 0, col = 0;
        for (String label : buttonLabels) {
            Button button = createButton(label);
            buttonGrid.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        Button clearButton = new Button("C");
        clearButton.setPrefSize(90, 50);
        clearButton.setStyle("-fx-font-size: 18;");
        clearButton.setOnAction(e -> clear());

        VBox root = new VBox(15, display, buttonGrid, clearButton);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 250, 400);
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.setPrefSize(50, 50);
        button.setStyle("-fx-font-size: 18;");
        button.setOnAction(e -> handleButtonClick(label));
        return button;
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "+" -> handleOperator("+");
            case "-" -> handleOperator("-");
            case "*" -> handleOperator("*");
            case "/" -> handleOperator("/");
            case "=" -> calculate();
            case "." -> handleDecimalPoint();
            default -> handleNumber(label);
        }
    }

    private void handleNumber(String number) {
        if (startNewNumber) {
            display.clear();
            startNewNumber = false;
        }
        display.appendText(number);
    }

    private void handleOperator(String op) {
        if (!operator.isEmpty()) {
            calculate();
        }
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        startNewNumber = true;
    }

    private void handleDecimalPoint() {
        if (!display.getText().contains(".")) {
            display.appendText(".");
        }
    }

    private void calculate() {
        double secondNumber;
        try {
            secondNumber = Double.parseDouble(display.getText());
        } catch (NumberFormatException e) {
            display.setText("Ошибка");
            return;
        }

        double result;
        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> {
                if (secondNumber == 0) {
                    display.setText("Ошибка: деление на 0");
                    return;
                }
                result = firstNumber / secondNumber;
            }
            default -> {
                display.setText("Ошибка");
                return;
            }
        }

        display.setText(String.valueOf(result));
        operator = "";
        startNewNumber = true;
    }

    private void clear() {
        display.clear();
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
