package br.edu.ifsp.poos3.practical03.persistence;

import br.edu.ifsp.poos3.practical03.model.Consultor;
import br.edu.ifsp.poos3.practical03.model.Funcionario;
import br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo;
import br.edu.ifsp.poos3.practical03.model.Revendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class SqliteFuncionarioDao implements FuncionarioDao {

    @Override
    public void salvar(Funcionario funcionario) {
        throw new UnsupportedOperationException("Método ainda não implementado");
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        throw new UnsupportedOperationException("Método ainda não implementado");
    }

    @Override
    public void deletar(String cpf) {
        throw new UnsupportedOperationException("Método ainda não implementado");
    }

    @Override
    public Funcionario buscar(String cpf) {
        throw new UnsupportedOperationException("Método não pedido na prova");
    }


    @Override
    public List<Funcionario> buscarTodos() {
        final Map<String, List<Funcionario>> responsaveisSubordinados = new LinkedHashMap<>();
        final Map<String, Funcionario> funcionarios = new LinkedHashMap<>();
        try {
            String sql = "SELECT * FROM funcionario";
            final PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
            final ResultSet result = stmt.executeQuery();

            while(result.next()){
                final String tipo = result.getString("tipo");
                final String cpf = result.getString("cpf");
                final String nome = result.getString("nome");
                final String sexo = result.getString("sexo");
                final int idade = result.getInt("idade");
                final double vendido = result.getDouble("valor_vendido");
                final String responsavel = result.getString("responsavel");

                final Funcionario funcionario;

                if(tipo.equals("CONSULTOR")) funcionario = new Consultor(cpf, nome, idade, Sexo.valueOf(sexo), vendido, null);
                else funcionario = new Revendedor(cpf, nome, idade, Sexo.valueOf(sexo), vendido, null);

                funcionarios.put(cpf, funcionario);

                if(!responsaveisSubordinados.containsKey(responsavel)){
                    responsaveisSubordinados.put(responsavel, new ArrayList<>());
                }

                final List<Funcionario> subordinados = responsaveisSubordinados.get(responsavel);
                subordinados.add(funcionario);
            }
            for (Map.Entry<String, List<Funcionario>> responsavelSubordinados : responsaveisSubordinados.entrySet()) {
                final String cpfResponsavel = responsavelSubordinados.getKey();
                if(funcionarios.get(cpfResponsavel) instanceof Revendedor) continue;
                final Consultor responsavel = (Consultor) funcionarios.get(cpfResponsavel);
                final List<Funcionario> subordinados = responsavelSubordinados.getValue();
                subordinados.forEach(subordinado -> subordinado.setResponsavel(responsavel));
            }
            return new ArrayList<>(funcionarios.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
