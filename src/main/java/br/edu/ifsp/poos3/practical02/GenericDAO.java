package br.edu.ifsp.poos3.practical02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GenericDAO <T, K>{

    void salvar(T tipo);
    void atualizar(T tipo);
    void deletar(K key);
    T buscar(K key);
    List<T> buscarTodos();
}
