module com.proyectoloteriamexicana {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.proyectoloteriamexicana to javafx.fxml;
    exports com.proyectoloteriamexicana;
}