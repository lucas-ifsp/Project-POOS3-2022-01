package br.edu.ifsp.poos3.practical03.controller;

import br.edu.ifsp.poos3.practical03.model.Consultor;
import br.edu.ifsp.poos3.practical03.model.Funcionario;
import br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo;
import br.edu.ifsp.poos3.practical03.model.Revendedor;
import br.edu.ifsp.poos3.practical03.persistence.FuncionarioDao;
import br.edu.ifsp.poos3.practical03.persistence.SqliteFuncionarioDao;
import br.edu.ifsp.poos3.practical03.view.FuncionarioView;
import br.edu.ifsp.poos3.practical03.view.FuncionarioView.ModoView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.stream.Collectors;

import static br.edu.ifsp.poos3.practical03.view.FuncionarioView.ModoView.EDITAR;
import static br.edu.ifsp.poos3.practical03.view.FuncionarioView.ModoView.NOVO;

public class FuncionarioController {

    @FXML private Button btnCancelar;
    @FXML private Button btnSalvar;
    @FXML private ComboBox<String> cbResponsavel;
    @FXML private ComboBox<Sexo> cbSexo;
    @FXML private Label lbComissao;
    @FXML private Label lbSubordinados;
    @FXML private TextField txtCPF;
    @FXML private TextField txtIdade;
    @FXML private TextField txtNome;
    @FXML private TextField txtValor;

    private Funcionario funcionario = null;
    private ModoView modo;
    private List<Funcionario> funcionarios;

    @FXML
    private void initialize(){
        cbSexo.setItems(FXCollections.observableArrayList(Sexo.values()));
        FuncionarioDao dao = new SqliteFuncionarioDao();
        funcionarios = dao.buscarTodos();
        final List<String> cpfResponsaveis = funcionarios.stream()
                .map(Funcionario::getCpf)
                .collect(Collectors.toList());
        cbResponsavel.setItems(FXCollections.observableArrayList(cpfResponsaveis));
    }

    @FXML void executar(ActionEvent event) {
        FuncionarioDao dao = new SqliteFuncionarioDao();
        switch (modo){
            case NOVO -> {
                final Funcionario novo = getFuncionarioFromView();
                final Funcionario responsavel = funcionarios.stream()
                        .filter(one -> one.getCpf().equals(novo.getCpfResponsavel()))
                        .findAny()
                        .orElseThrow();

                if(responsavel instanceof Revendedor revendedor) {
                    Consultor consultor = new Consultor(revendedor);
                    dao.atualizar(consultor);
                }
                dao.salvar(novo);
            }
            case EDITAR -> dao.atualizar(getFuncionarioFromView());
        }
        fechar();
    }

    public void setFuncionarioIntoView(Funcionario funcionario) {
        this.funcionario = funcionario;

        txtCPF.setText(funcionario.getCpf());
        txtNome.setText(funcionario.getNome());
        txtIdade.setText(String.valueOf(funcionario.getIdade()));
        txtValor.setText(String.valueOf(funcionario.getValorVendido()));
        cbSexo.getSelectionModel().select(funcionario.getSexo());
        cbResponsavel.getSelectionModel().select(funcionario.getCpfResponsavel());
        lbComissao.setText(String.valueOf(funcionario.calculaComissao()));

        if (funcionario instanceof Consultor consultor) {
            lbSubordinados.setText(String.valueOf(consultor.numeroDeSubordinados()));
        }
    }
    private Funcionario getFuncionarioFromView(){
        final String cpf = txtCPF.getText();
        final String nome = txtNome.getText();
        final double valorVendido = Double.parseDouble(txtValor.getText());
        final int idade = Integer.parseInt(txtIdade.getText());
        final Sexo sexo = cbSexo.getSelectionModel().getSelectedItem();
        final String cpfResponsavel = cbResponsavel.getSelectionModel().getSelectedItem();

        if(funcionario == null) funcionario = new Revendedor(cpf);

        funcionario.setNome(nome);
        funcionario.setIdade(idade);
        funcionario.setSexo(sexo);
        funcionario.setValorVendido(valorVendido);
        funcionario.setResponsavel(new Consultor(cpfResponsavel));
        return funcionario;
    }

    @FXML void cancelar(ActionEvent event) {
        fechar();
    }


    public void configuraExibicao(ModoView modo) {
        this.modo = modo;
        if(modo == NOVO) return;

        txtCPF.setDisable(true);
        cbResponsavel.setDisable(true);

        if(modo == EDITAR){
            btnSalvar.setText("Atualizar");
            return;
        }

        txtNome.setDisable(true);
        txtIdade.setDisable(true);
        txtValor.setDisable(true);
        cbSexo.setDisable(true);
        lbComissao.setDisable(true);
        btnCancelar.setVisible(false);
        btnSalvar.setText("Fechar");
    }

    private void fechar() {
        final Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
