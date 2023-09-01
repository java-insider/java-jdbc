package jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectCustomers {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            System.out.println("Connected successfully!");

            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, name, email, age FROM customer ORDER BY name")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        int age = rs.getInt("age");

                        System.out.format("ID = %d, name = %s, email = %s, age = %d\n", id, name, email, age);
                    }
                }
            }
        }
    }
}
