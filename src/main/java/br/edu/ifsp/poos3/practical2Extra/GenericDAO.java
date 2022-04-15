package br.edu.ifsp.poos3.practical2Extra;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public abstract class GenericDAO <K, T extends EntidadePersistente<K>>{

    protected abstract Map<K,T> db();

    public void salvar(T entidade){
        Objects.requireNonNull(entidade, "Entidade não pode ser nula");
        db().put(entidade.primaryKey(), entidade);
    }

    public void atualizar(T entidade){
        Objects.requireNonNull(entidade, "Entidade não pode ser nula");
        db().replace(entidade.primaryKey(), entidade);
    }

    public void deletar(K key){
        db().remove(key);
    }

    public T buscar(K key){
        return db().get(key);
    }

    public Collection<T> buscarTodos(){
        return db().values();
    }
}
