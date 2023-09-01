package jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertCustomersPreparedStatement {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            System.out.println("Connected successfully!");

//            try (Statement stmt =  conn.createStatement()) {
//                stmt.executeUpdate("INSERT INTO customer (name, email, age) VALUES ('Pedro', 'pedro@email.com', 20)");
//            }

            // -> Previne: SQL Injection
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer (name, email, age) VALUES (?, ?, ?)")) {
                stmt.setString(1, "Paulo");
                stmt.setString(2, "paulo@email.com");
                stmt.setInt(3, 25);
                stmt.executeUpdate();
            }

            System.out.println("Customer inserted!");
        }
    }
}
