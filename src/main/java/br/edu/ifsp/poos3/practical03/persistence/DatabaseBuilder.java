package br.edu.ifsp.poos3.practical03.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import static br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo.FEMININO;
import static br.edu.ifsp.poos3.practical03.model.Funcionario.Sexo.MASCULINO;

public class DatabaseBuilder {

    public static void main(String[] args) throws SQLException, IOException {
        dropTableIfExists();
        createTable();
        populate();
    }

    private static void dropTableIfExists() throws IOException {
        final Path path = Paths.get("database.db");
        if(Files.exists(path)) Files.delete(path);
    }

    private static void createTable() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        final Statement stmt = connection.createStatement();
        String sql = """
                CREATE TABLE funcionario(
                    cpf TEXT NOT NULL PRIMARY KEY,
                    nome TEXT,
                    idade INTEGER,
                    sexo TEXT,
                    valor_vendido REAL,
                    tipo TEXT,
                    responsavel TEXT,
                    FOREIGN KEY (responsavel) REFERENCES funcionario(cpf)
                )
                """;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    private static void populate() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        final Statement stmt = connection.createStatement();

        final String sql = "INSERT INTO funcionario (cpf, nome, idade, sexo, valor_vendido, tipo, responsavel) " +
                "VALUES ('%s', '%s', %d, '%s', %.2f, '%s', '%s')";

        stmt.addBatch(String.format(sql, "12312312312", "David A. Huffman", 74, MASCULINO, 7000.00, "CONSULTOR", ""));
        stmt.addBatch(String.format(sql, "32132132131", "Augusta Ada Byron", 36, FEMININO, 3000.00, "CONSULTOR", "12312312312"));
        stmt.addBatch(String.format(sql, "21321321313", "Edsger Dijkstra", 72, MASCULINO, 1520.00, "CONSULTOR", "12312312312"));
        stmt.addBatch(String.format(sql, "45645645646", "Alan Mathison Turing", 41, MASCULINO, 780.00, "CONSULTOR", "32132132131"));
        stmt.addBatch(String.format(sql, "65465465464", "John von Neumann", 53, MASCULINO, 300.00, "REVENDEDOR", "45645645646"));
        stmt.addBatch(String.format(sql, "90219021902", "Donald Ervin Knuth", 80, MASCULINO, 432.00, "REVENDEDOR", "45645645646"));
        stmt.addBatch(String.format(sql, "54654654654", "Grace Murray Hopper", 85, FEMININO, 432.00, "REVENDEDOR", "21321321313"));
        stmt.executeBatch();

        stmt.close();
        connection.close();
    }
}
