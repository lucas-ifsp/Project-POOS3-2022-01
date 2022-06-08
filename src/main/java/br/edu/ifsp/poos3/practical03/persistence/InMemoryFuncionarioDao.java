package br.edu.ifsp.poos3.practical03.persistence;

import br.edu.ifsp.poos3.practical03.model.Consultor;
import br.edu.ifsp.poos3.practical03.model.Funcionario;
import br.edu.ifsp.poos3.practical03.model.Revendedor;

import java.util.*;

import static br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo.FEMININO;
import static br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo.MASCULINO;

public final class InMemoryFuncionarioDao implements FuncionarioDao {

    private static final Map<String, Funcionario> db = new LinkedHashMap<>();

    static {
        Consultor huffman = new Consultor("12312312312", "David A. Huffman", 74, MASCULINO, 7000.00, null);
        Consultor ada = new Consultor("32132132131", "Augusta Ada Byron", 36, FEMININO, 3000.00, huffman);
        Consultor dijkstra = new Consultor("21321321313", "Edsger Dijkstra", 72, MASCULINO, 1520.00, huffman);

        FuncionarioDao dao = new InMemoryFuncionarioDao();
        dao.salvar(huffman);
        dao.salvar(ada);
        dao.salvar(dijkstra);
    }

    public void salvar(Funcionario entidade){
        Objects.requireNonNull(entidade, "Entidade não pode ser nula");
        db.put(entidade.getCpf(), entidade);
    }

    public void atualizar(Funcionario entidade){
        Objects.requireNonNull(entidade, "Entidade não pode ser nula");
        db.replace(entidade.getCpf(), entidade);
    }

    public void deletar(String key){
        db.remove(key);
    }

    public Funcionario buscar(String key){
        return db.get(key);
    }

    public List<Funcionario> buscarTodos(){
        return new ArrayList<>(db.values());
    }
}
