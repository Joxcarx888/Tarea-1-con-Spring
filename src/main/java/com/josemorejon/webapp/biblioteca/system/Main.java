package com.josemorejon.webapp.biblioteca.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.josemorejon.webapp.biblioteca.BibliotecaApplication;
import com.josemorejon.webapp.biblioteca.controller.FXController.IndexController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;
    private Scene scene;

    private ApplicationContext applicationContext;  // Variable para almacenar el contexto de Spring

    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(BibliotecaApplication.class).run();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        stage.setTitle("Biblioteca SpringBoot");
        indexView();
        stage.show();

    }

    public Initializable cambiarEscena(String fxmlName, int width, int height) throws IOException{
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane)loader.load(archivo), width, height);

        stage.setScene(scene);
        stage.sizeToScene();

        initializable = (Initializable)loader.getController();

        return initializable;
    }

    public void indexView(){
        try {
            IndexController indexView = (IndexController)cambiarEscena("index.fxml", 600, 400);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
