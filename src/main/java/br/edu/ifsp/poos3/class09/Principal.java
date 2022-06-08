package br.edu.ifsp.poos3.class09;

import java.sql.*;

public class Principal {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
            Product product = new Product("SKU-CRT-UVA-01", "Corote de Uva", 1.99, 3000);
            //insert(product);
            product.setName("Corote Uva Espacial");
            product.setQuantity(40000);
            product.setPrice(2.99);
            //update(product);
            findOne(product.getSku());
            //delete(product.getSku());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void delete(String key) throws SQLException {
        String capeta = "DELETE FROM products WHERE sku = ?";
        final PreparedStatement stmt = connection.prepareStatement(capeta);
        stmt.setString(1, key);
        stmt.executeUpdate();
    }

    private static void findOne(String key) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
        String sql = "SELECT * FROM products WHERE sku = ?";
        final PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, key);
        final ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            final String retrievedProduct = String.format("[%s] Name = %s | Price = %.2f | Quantity = %d",
                    rs.getString("sku"), rs.getString("name"),
                    rs.getDouble("price"), rs.getInt("quantity"));
            System.out.println(retrievedProduct);
        }
    }

    private static void update(Product product) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE sku = ?";
        final PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setDouble(2, product.getPrice());
        stmt.setInt(3, product.getQuantity());
        stmt.setString(4, product.getSku());
        stmt.executeUpdate();
    }

    private static void insert(Product product) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
        String sql = "INSERT INTO products(sku, name, price, quantity) VALUES (?, ?, ?, ?)";
        final PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, product.getSku());
        stmt.setString(2, product.getName());
        stmt.setDouble(3, product.getPrice());
        stmt.setInt(4, product.getQuantity());
        stmt.executeUpdate();
    }
}
