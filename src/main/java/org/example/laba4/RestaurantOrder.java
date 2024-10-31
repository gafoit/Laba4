package org.example.laba4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestaurantOrder extends Application {

    private final Dish[] menu = {
            new Dish("Салат Цезарь", 300),
            new Dish("Паста Карбонара", 450),
            new Dish("Пицца Маргарита", 600),
            new Dish("Суп Том Ям", 400),
    };


    private final CheckBox[] dishCheckBoxes = new CheckBox[menu.length];
    private final Spinner<Integer>[] quantitySpinners = new Spinner[menu.length];

    @Override
    public void start(Stage primaryStage) {
        VBox menuBox = new VBox(10);
        menuBox.setPadding(new Insets(15));

        for (int i = 0; i < menu.length; i++) {
            Dish dish = menu[i];

            CheckBox checkBox = new CheckBox(dish.name);
            checkBox.setSelected(false);


            Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
            quantitySpinner.setDisable(true); // Доступен только если блюдо выбрано

            checkBox.setOnAction(e -> quantitySpinner.setDisable(!checkBox.isSelected()));


            dishCheckBoxes[i] = checkBox;
            quantitySpinners[i] = quantitySpinner;

            HBox dishRow = new HBox(10, checkBox, new Label("Количество:"), quantitySpinner);
            menuBox.getChildren().add(dishRow);
        }
        Button calculateButton = new Button("Рассчитать чек");
        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);

        calculateButton.setOnAction(e -> calculateTotal(receiptArea));

        VBox root = new VBox(10, menuBox, calculateButton, new Label("Чек:"), receiptArea);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Заказ в ресторане");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateTotal(TextArea receiptArea) {
        StringBuilder receipt = new StringBuilder();
        double totalOrderCost = 0.0;

        receipt.append("Ваш заказ:\n\n");

        for (int i = 0; i < menu.length; i++) {
            if (dishCheckBoxes[i].isSelected()) {
                Dish dish = menu[i];
                int quantity = quantitySpinners[i].getValue();
                double totalCost = dish.price * quantity;

                receipt.append(String.format("%s - %d порций, цена за порцию: %.2f, итог: %.2f руб.\n",
                        dish.name, quantity, dish.price, totalCost));

                totalOrderCost += totalCost;
            }
        }

        receipt.append("\nОбщая стоимость заказа: ").append(String.format("%.2f руб.", totalOrderCost));
        receiptArea.setText(receipt.toString());
    }


    static class Dish {
        String name;
        double price;

        Dish(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}