package jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CountCostumers {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            System.out.println("Connected successfully!");

            try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM customer")) {
                ResultSet rs = stmt.executeQuery();

                rs.next();
                int count = rs.getInt(1);
                System.out.println(count);
            }
        }
    }
}
