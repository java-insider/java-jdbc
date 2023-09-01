package jdbc.core;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConn {

    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin");
        System.out.println("Connected successfully!");
        conn.close();
    }
}
