package jdbc.dao.example;

import jdbc.dao.core.DAO;
import jdbc.dao.core.DAOException;
import jdbc.dao.core.DBConn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneDAO extends DAO<Phone, Integer> {

    public PhoneDAO(DBConn conn) {
        super(conn);
    }

    @Override
    public Integer insert(Phone phone) {
        try (PreparedStatement stmt = conn.statement("INSERT INTO phone (customer_id, description, number) VALUES (?, ?, ?)")) {
            stmt.setInt(1, phone.customerId());
            stmt.setString(2, phone.description());
            stmt.setString(3, phone.number());
            stmt.executeUpdate();

            try (ResultSet keysRS = stmt.getGeneratedKeys()) {
                keysRS.next();
                return keysRS.getInt(1);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
