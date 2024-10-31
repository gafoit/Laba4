module org.example.laba4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.laba4 to javafx.fxml;
    exports org.example.laba4;
}