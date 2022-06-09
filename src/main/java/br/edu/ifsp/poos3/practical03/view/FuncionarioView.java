package br.edu.ifsp.poos3.practical03.view;

import br.edu.ifsp.poos3.practical03.controller.FuncionarioController;
import br.edu.ifsp.poos3.practical03.model.Funcionario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FuncionarioView {

    public enum ModoView{NOVO,EDITAR,VER}

    public void showAndWait(Funcionario funcionario, ModoView modo){
        FXMLLoader loader = new FXMLLoader();
        final Pane graph;
        try {
            graph = loader.load(Objects.requireNonNull(getClass().getResource("funcionario.fxml")).openStream());
            Scene scene = new Scene(graph, 600, 260);

            FuncionarioController controller = loader.getController();

            if(funcionario != null)
                controller.setFuncionarioIntoView(funcionario);

            controller.configuraExibicao(modo);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Funcionário");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
