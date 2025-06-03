package model;

import util.DBConnection;
import java.sql.*;
import java.util.List;

public class TransactionDAO {

    public static boolean saveTransaction(Transaction trx) {
        String insertTransaction = "INSERT INTO transactions (user_id, total_amount, cash_received, change_amount) VALUES (?, ?, ?, ?)";
        String insertDetail = "INSERT INTO transaction_details (transaction_id, product_id, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        String updateStock = "UPDATE products SET stock = stock - ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(insertTransaction, Statement.RETURN_GENERATED_KEYS)) {
                stmt1.setInt(1, trx.getUserId());
                stmt1.setDouble(2, trx.getTotalAmount());
                stmt1.setDouble(3, trx.getCashReceived());
                stmt1.setDouble(4, trx.getChangeAmount());
                stmt1.executeUpdate();

                ResultSet rs = stmt1.getGeneratedKeys();
                if (rs.next()) {
                    trx.setId(rs.getInt(1));
                }

                try (PreparedStatement stmt2 = conn.prepareStatement(insertDetail);
                     PreparedStatement stmt3 = conn.prepareStatement(updateStock)) {
                    for (TransactionDetail d : trx.getDetails()) {
                        stmt2.setInt(1, trx.getId());
                        stmt2.setInt(2, d.getProductId());
                        stmt2.setInt(3, d.getQuantity());
                        stmt2.setDouble(4, d.getUnitPrice());
                        stmt2.setDouble(5, d.getSubtotal());
                        stmt2.addBatch();

                        stmt3.setInt(1, d.getQuantity());
                        stmt3.setInt(2, d.getProductId());
                        stmt3.addBatch();
                    }
                    stmt2.executeBatch();
                    stmt3.executeBatch();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


