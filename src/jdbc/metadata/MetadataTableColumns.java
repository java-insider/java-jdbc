package jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class MetadataTableColumns {

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "admin")) {
            System.out.println("Connected successfully!");

            DatabaseMetaData dbmd = conn.getMetaData();

            try (ResultSet rs = dbmd.getColumns(null, null, "customer", null)) {
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    String columnSize = rs.getString("COLUMN_SIZE");
                    String datatype = rs.getString("DATA_TYPE");
                    String isNullable = rs.getString("IS_NULLABLE");
                    String isAutoIncrement = rs.getString("IS_AUTOINCREMENT");

                    System.out.format("%s, %s, %s, %s, %s\n", columnName, columnSize, datatype, isNullable, isAutoIncrement);
                }
            }
        }
    }
}
