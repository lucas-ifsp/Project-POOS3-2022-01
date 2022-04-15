package br.edu.ifsp.poos3.practical2Extra;

import java.util.*;

public final class FuncionarioDAO extends GenericDAO<String, Funcionario> {

    private static final Map<String, Funcionario> db = new LinkedHashMap<>();

    @Override
    protected Map<String, Funcionario> db() {
        return db;
    }
}
