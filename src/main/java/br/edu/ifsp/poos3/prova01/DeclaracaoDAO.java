package br.edu.ifsp.poos3.prova01;

import java.util.*;

public class DeclaracaoDAO{// extends GenericDAO<Integer, Declaracao> {

    private static final Map<Integer, Declaracao> db = new LinkedHashMap<>();

    public void salvar(Declaracao declaracao){
        Objects.requireNonNull(declaracao, "Entidade não pode ser nula");
        db.put(declaracao.getId(), declaracao);
    }

    public void atualizar(Declaracao declaracao){
        Objects.requireNonNull(declaracao, "Entidade não pode ser nula");
        db.replace(declaracao.getId(), declaracao);
    }

    public void remover(Integer id){
        db.remove(id);
    }

    public Optional<Declaracao> buscar(Integer id){
        return Optional.ofNullable(db.get(id));
    }

    public List<Declaracao> buscarTodos(){
        return new ArrayList<>(db.values());
    }

    /*@Override
    protected Map<Integer, Declaracao> db() {
        return db;
    }*/
}
