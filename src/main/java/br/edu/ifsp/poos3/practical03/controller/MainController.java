package br.edu.ifsp.poos3.practical03.controller;

import br.edu.ifsp.poos3.practical03.model.Funcionario;
import br.edu.ifsp.poos3.practical03.persistence.FuncionarioDao;
import br.edu.ifsp.poos3.practical03.persistence.InMemoryFuncionarioDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    @FXML private TableView<Funcionario> table;
    @FXML private TableColumn<Funcionario, String> cCPF;
    @FXML private TableColumn<Funcionario, String> cGenero;
    @FXML private TableColumn<Funcionario, Integer> cIdade;
    @FXML private TableColumn<Funcionario, String> cNome;
    @FXML private TableColumn<Funcionario, String> cResposavel;
    @FXML private TableColumn<Funcionario, Double> cVendido;
    @FXML private TextField txtFiltrar;

    private ObservableList<Funcionario> funcionariosTabela;
    private List<Funcionario> funcionarios;

    @FXML
    private void initialize(){
        bindEntitiesToColumns();
        bindTableToDataSource();
        loadData();
    }

    private void bindEntitiesToColumns() {
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cGenero.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        cIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cResposavel.setCellValueFactory(new PropertyValueFactory<>("cpfResponsavel"));
        cVendido.setCellValueFactory(new PropertyValueFactory<>("valorVendido"));
    }

    private void bindTableToDataSource() {
        funcionariosTabela = FXCollections.observableArrayList();
        table.setItems(funcionariosTabela);
    }

    private void loadData() {
        FuncionarioDao dao = new InMemoryFuncionarioDao();
        funcionarios = dao.buscarTodos();
        funcionariosTabela.clear();
        funcionariosTabela.addAll(funcionarios);
    }

    public void filtrar(KeyEvent keyEvent) {
        final String textoFiltro = txtFiltrar.getText().toLowerCase();
        funcionariosTabela.clear();

        if(textoFiltro.isEmpty()) {
            funcionariosTabela.addAll(funcionarios);
            return;
        }

        final List<Funcionario> funcionariosFiltrados = funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().toLowerCase().contains(textoFiltro))
                .collect(Collectors.toList());

        funcionariosTabela.addAll(funcionariosFiltrados);
    }

    public void novo(ActionEvent actionEvent) {

    }

    public void editar(ActionEvent actionEvent) {

    }

    public void detalhar(ActionEvent actionEvent) {

    }

    public void remover(ActionEvent actionEvent) {

    }
}
