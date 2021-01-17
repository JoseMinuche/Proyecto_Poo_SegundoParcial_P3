module com.proyectoloteriamexicana {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.proyectoloteriamexicana to javafx.fxml;
    exports com.proyectoloteriamexicana;
}