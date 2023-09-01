package jdbc.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class BatchInsertWithMultipleTransactions {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int batchMaxSize = 50;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer (name, email, age) VALUES (?, ?, ?)")) {

                conn.setAutoCommit(false);
                for (int i = 1; i <= 100; i++) {
                    stmt.setString(1, String.format("Customer %03d", i));
                    stmt.setString(2, String.format("c%03d@email.com", i));
                    stmt.setInt(3, random.nextInt(10, 80));

                    stmt.addBatch();

                    if (i % batchMaxSize == 0) {
                        stmt.executeBatch();
                        conn.commit();
                        conn.setAutoCommit(false);
                    }
                }

                stmt.executeBatch();
                conn.commit();
            }
        }
    }
}
