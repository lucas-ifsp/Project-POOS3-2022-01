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
        String sql = """
                INSERT INTO funcionario(
                    cpf,
                    nome,
                    idade,
                    sexo,
                    valor_vendido,
                    responsavel,
                    tipo
                ) VALUES (?, ?, ?, ?, ?, ?, ?) 
               
                """;
        try {
            final PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setInt(3, funcionario.getIdade());
            stmt.setString(4, funcionario.getSexo().toString());
            stmt.setDouble(5, funcionario.getValorVendido());

            if(funcionario.getResponsavel() == null) stmt.setNull(6, Types.NULL);
            else stmt.setString(6, funcionario.getCpfResponsavel());

            if(funcionario instanceof Revendedor) stmt.setString(7, "REVENDEDOR");
            else stmt.setString(7, "CONSULTOR");

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        String sql = """
                UPDATE funcionario SET
                    nome = ?,
                    idade = ?,
                    sexo = ?,
                    valor_vendido = ?,
                    responsavel = ?,
                    tipo = ? 
                WHERE cpf = ?
                """;
        try {
            final PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);

            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdade());
            stmt.setString(3, funcionario.getSexo().toString());
            stmt.setDouble(4, funcionario.getValorVendido());

            if(funcionario.getResponsavel() == null) stmt.setNull(5, Types.NULL);
            else stmt.setString(5, funcionario.getCpfResponsavel());

            if(funcionario instanceof Revendedor) stmt.setString(6, "REVENDEDOR");
            else stmt.setString(6, "CONSULTOR");

            stmt.setString(7, funcionario.getCpf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(String cpf) {
        String sql = """
                DELETE FROM funcionario WHERE cpf = ?
                """;
        try {
            final PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
