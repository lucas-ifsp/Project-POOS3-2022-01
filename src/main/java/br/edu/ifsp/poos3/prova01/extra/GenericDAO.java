package br.edu.ifsp.poos3.prova01.extra;

import java.util.*;

public abstract class GenericDAO <K,V extends Entidade<K>> {

    protected abstract Map<K,V> db();

    public <T extends V> void salvar(T tipo){
        Objects.requireNonNull(tipo, "Entidade não pode ser nula.");
        db().put(tipo.chavePrimaria(), tipo);
    }

    public <T extends V> void atualizar(T tipo){
        Objects.requireNonNull(tipo, "Entidade não pode ser nula.");
        db().replace(tipo.chavePrimaria(), tipo);
    }

    public void remover(K chave){
        db().remove(chave);
    }

    public Optional<V> buscar(K chave){
        return Optional.of(db().get(chave));
    }

    public List<V> buscarTodos(){
        return new ArrayList<>(db().values());
    }
}
