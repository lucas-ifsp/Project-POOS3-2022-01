package br.edu.ifsp.poos3.class09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void main(String[] args) {
        try {
            final Connection connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
            String sqlFromCreateTableProduct = """
                        CREATE TABLE products (
                            sku TEXT NOT NULL,
                            name TEXT,
                            price REAL,
                            quantity INTEGER,
                            PRIMARY KEY (sku)
                        )
                    """;

            String sqlFromCreateTableSales = """
                        CREATE TABLE sale (
                    	    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    	    quantity INTEGER NOT NULL,
                    	    seller	TEXT,
                    	    total REAL,
                    	    product	TEXT,
                    	    FOREIGN KEY(product) REFERENCES products(sku)
                    )
                    """;

            final Statement stmt = connection.createStatement();
            stmt.addBatch(sqlFromCreateTableProduct);
            stmt.addBatch(sqlFromCreateTableSales);
            stmt.executeBatch();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
