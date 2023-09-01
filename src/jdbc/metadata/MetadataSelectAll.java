package jdbc.metadata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetadataSelectAll {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            System.out.println("Connected successfully!");

            DatabaseMetaData dbmd = conn.getMetaData();

            List<String> columns = new ArrayList<>();

            try (ResultSet rs = dbmd.getColumns(null, null, "customer", null)) {
                while (rs.next()) {
                    columns.add(rs.getString("COLUMN_NAME"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    for (String column : columns) {
                        System.out.format("%-17s", column);
                    }
                    System.out.println();

                    while (rs.next()) {
                        StringBuilder sb = new StringBuilder();
                        for (String column : columns) {
                            String value = rs.getString(column);
                            sb.append(String.format("%-17s", value));
                        }

                        System.out.println(sb);
                    }
                }
            }
        }
    }
}
