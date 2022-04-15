package br.edu.ifsp.poos3.practical02;

import java.util.*;

public final class FuncionarioDAO implements GenericDAO<Funcionario, String>{

    private static final Map<String, Funcionario> db = new LinkedHashMap<>();

    public void salvar(Funcionario funcionario){
        Objects.requireNonNull(funcionario, "Funcionário não pode ser nulo.");
        db.put(funcionario.getCpf(), funcionario);
    }

    public void atualizar(Funcionario funcionario){
        Objects.requireNonNull(funcionario, "Funcionário não pode ser nulo.");
        db.replace(funcionario.getCpf(), funcionario);
    }

    public void deletar(String cpf){
        Objects.requireNonNull(cpf, "CPF não pode ser nulo.");
        if(cpf.isEmpty()) throw new IllegalArgumentException("CPF não pode ser vazio.");
        db.remove(cpf);
    }

    public Funcionario buscar(String cpf){
        return db.get(cpf);
    }

    public List<Funcionario> buscarTodos(){
        return new ArrayList<>(db.values());
    }
}
