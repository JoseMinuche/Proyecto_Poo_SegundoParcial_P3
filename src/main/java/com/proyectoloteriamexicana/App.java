package com.proyectoloteriamexicana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception 
    {
        scene = loadScene("Principal",310, 460);
        scene = new Scene(loadFXML("Principal"),310, 460);
        stage.setTitle("Loteria Mexicana v1.0");
        stage.resizableProperty().setValue(Boolean.FALSE);        
        stage.setScene(scene);
        stage.show();                                
    }

    public static void setRoot(String fxml,int width, int height) throws IOException {       
        scene.setRoot(loadFXML(fxml)); 
        //scene = loadScene(fxml, width, height);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    private static Scene loadScene(String fxml, int width, int height)throws IOException
    {
        return new Scene(loadFXML(fxml),width,height);
    }

    public static void main(String[] args) {
        launch();
    }

}