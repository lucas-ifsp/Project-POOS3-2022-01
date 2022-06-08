package br.edu.ifsp.poos3.practical03.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(graph, 783, 600);
        stage.setScene(scene);
        stage.setTitle("Gestão de Funcionários");
        stage.show();
    }
}
