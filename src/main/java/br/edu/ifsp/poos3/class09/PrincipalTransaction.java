package br.edu.ifsp.poos3.class09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrincipalTransaction {
    private Connection connection;

    public static void main(String[] args) {
        PrincipalTransaction main = new PrincipalTransaction();
        final Product product = new Product("SKU-HMP-POWER_PLUS", "Ganja Plus", 5.99, 300);
        //main.insert(product);
        main.sell(product, 40, "Maria");
    }

    private void sell(Product product, int quantity, String seller)  {
        String sqlProduct = "UPDATE products SET quantity = ? WHERE sku = ?";
        String sqlSale = "INSERT INTO sale (quantity, total, seller, product) values (?, ?, ?, ?)";

        try (var connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
             var stmtProduct = connection.prepareStatement(sqlProduct);
             var stmtSale = connection.prepareStatement(sqlSale)){

            connection.setAutoCommit(false);

            int newQuantity = product.getQuantity() - quantity;
            stmtProduct.setInt(1, newQuantity);
            stmtProduct.setString(2, product.getSku());
            stmtProduct.executeUpdate();

            final double totalPrice = quantity * product.getPrice();

            stmtSale.setInt(1, quantity);
            stmtSale.setDouble(2, totalPrice);
            stmtSale.setString(3, seller);
            stmtSale.setString(4, product.getSku());
            stmtSale.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            //System.out.println("Erro na venda!");
            //connection.rollback();
            e.printStackTrace();
        }
    }

    private void insert(Product product) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
        String sql = "INSERT INTO products(sku, name, price, quantity) VALUES (?, ?, ?, ?)";
        final PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, product.getSku());
        stmt.setString(2, product.getName());
        stmt.setDouble(3, product.getPrice());
        stmt.setInt(4, product.getQuantity());
        stmt.executeUpdate();
    }

    private void update(Product product) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:filo.db");
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE sku = ?";
        final PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, product.getName());
        stmt.setDouble(2, product.getPrice());
        stmt.setInt(3, product.getQuantity());
        stmt.setString(4, product.getSku());
        stmt.executeUpdate();
    }
}
