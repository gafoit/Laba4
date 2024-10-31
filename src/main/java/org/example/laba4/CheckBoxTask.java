package org.example.laba4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CheckBoxTask extends Application {
    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Это метка");
        TextField textField = new TextField("Это текстовое поле");
        Button button = new Button("Это кнопка");

        CheckBox labelCheckBox = new CheckBox("Показать метку");
        CheckBox textFieldCheckBox = new CheckBox("Показать текстовое поле");
        CheckBox buttonCheckBox = new CheckBox("Показать кнопку");

        labelCheckBox.setSelected(true);
        labelCheckBox.setOnAction(e -> label.setVisible(labelCheckBox.isSelected()));

        textFieldCheckBox.setSelected(true);
        textFieldCheckBox.setOnAction(e -> textField.setVisible(textFieldCheckBox.isSelected()));

        buttonCheckBox.setSelected(true);
        buttonCheckBox.setOnAction(e -> button.setVisible(buttonCheckBox.isSelected()));


        VBox widgetBox = new VBox(10, label, textField, button);
        VBox checkBoxBox = new VBox(10, labelCheckBox, textFieldCheckBox, buttonCheckBox);

        HBox root = new HBox(20, widgetBox, checkBoxBox);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Widget Visibility Controller");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
