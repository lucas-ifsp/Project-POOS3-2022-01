package br.edu.ifsp.poos3.practical03.persistence;

import br.edu.ifsp.poos3.practical03.model.Funcionario;

import java.util.List;

public interface FuncionarioDao {
    void salvar(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    void deletar(String key);
    Funcionario buscar(String key);
    List<Funcionario> buscarTodos();
}
