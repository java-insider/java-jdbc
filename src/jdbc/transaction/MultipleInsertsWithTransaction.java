package jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MultipleInsertsWithTransaction {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            try {
                conn.setAutoCommit(false);

                int customerId;

                try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO customer (name, email, age) VALUES (?, ?, ?)",
                    RETURN_GENERATED_KEYS
                )) {
                    stmt.setString(1, "Maria");
                    stmt.setString(2, "maria@email.com");
                    stmt.setInt(3, 30);
                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        rs.next();
                        customerId = rs.getInt(1);
                    }
                }

                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO phone (customer_id, description, number) VALUES (?, ?, ?)")) {
                    stmt.setInt(1, customerId);
                    stmt.setString(2, "Celular");
                    stmt.setString(3, "(21) 3498-49389");
                    stmt.executeUpdate();
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
