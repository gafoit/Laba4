package org.example.laba4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TextFlagApp extends Application {
    private final String[] colors = {"Красный", "Зелёный", "Синий", "Белый", "Жёлтый"};
    private final Rectangle stripe1 = new Rectangle(150, 50);
    private final Rectangle stripe2 = new Rectangle(150, 50);
    private final Rectangle stripe3 = new Rectangle(150, 50);

    @Override
    public void start(Stage primaryStage) {
        ToggleGroup stripe1Group = new ToggleGroup();
        ToggleGroup stripe2Group = new ToggleGroup();
        ToggleGroup stripe3Group = new ToggleGroup();

        VBox stripe1Box = createColorSelectionBox("Полоса 1", stripe1Group);
        VBox stripe2Box = createColorSelectionBox("Полоса 2", stripe2Group);
        VBox stripe3Box = createColorSelectionBox("Полоса 3", stripe3Group);

        stripe1.setFill(Color.LIGHTGRAY);
        stripe2.setFill(Color.LIGHTGRAY);
        stripe3.setFill(Color.LIGHTGRAY);

        Button drawButton = new Button("Нарисовать");
        Label resultLabel = new Label();

        drawButton.setOnAction(e -> {
            String color1 = getSelectedColor(stripe1Group);
            String color2 = getSelectedColor(stripe2Group);
            String color3 = getSelectedColor(stripe3Group);

            if (color1 != null && color2 != null && color3 != null) {
                resultLabel.setText(String.format("Выбранные цвета: %s, %s, %s", color1, color2, color3));
                stripe1.setFill(getColorFromName(color1));
                stripe2.setFill(getColorFromName(color2));
                stripe3.setFill(getColorFromName(color3));
            } else {
                resultLabel.setText("Выберите цвет для каждой полосы.");
            }
        });

        HBox flagBox = new HBox(15, stripe1Box, stripe2Box, stripe3Box);
        VBox flagDisplay = new VBox(0, stripe1, stripe2, stripe3);
        VBox root = new VBox(20, flagBox, drawButton, resultLabel, flagDisplay);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Текстовый флаг");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Запрещаем изменение размера окна
        primaryStage.show();
    }

    private VBox createColorSelectionBox(String label, ToggleGroup group) {
        VBox colorBox = new VBox(5);
        colorBox.getChildren().add(new Label(label));

        for (String color : colors) {
            RadioButton radioButton = new RadioButton(color);
            radioButton.setToggleGroup(group);
            colorBox.getChildren().add(radioButton);
        }

        return colorBox;
    }

    private String getSelectedColor(ToggleGroup group) {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        return selectedRadioButton != null ? selectedRadioButton.getText() : null;
    }

    private Color getColorFromName(String colorName) {
        return switch (colorName) {
            case "Красный" -> Color.RED;
            case "Зелёный" -> Color.GREEN;
            case "Синий" -> Color.BLUE;
            case "Белый" -> Color.WHITE;
            case "Жёлтый" -> Color.YELLOW;
            default -> Color.LIGHTGRAY;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}