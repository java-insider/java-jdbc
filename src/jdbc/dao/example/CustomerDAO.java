package jdbc.dao.example;

import jdbc.dao.core.DAO;
import jdbc.dao.core.DAOException;
import jdbc.dao.core.DBConn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DAO<Customer, Integer> {

    public CustomerDAO(DBConn conn) {
        super(conn);
    }

    @Override
    public Integer insert(Customer customer) {
        try (PreparedStatement stmt = conn.statement("INSERT INTO customer (name, email, age) VALUES (?, ?, ?)")) {
            stmt.setString(1, customer.name());
            stmt.setString(2, customer.email());
            stmt.setInt(3, customer.age());
            stmt.executeUpdate();

            try (ResultSet keysRS = stmt.getGeneratedKeys()) {
                keysRS.next();
                return keysRS.getInt(1);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Customer> list() {
        try (PreparedStatement stmt = conn.statement("SELECT id, name, email, age FROM customer")) {
            List<Customer> customers = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    customers.add(extractFromResultSet(rs));
                }

                return customers;
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement stmt = conn.statement("DELETE FROM customer WHERE id = ?")) {
            stmt.setInt(1, id);

            if (stmt.executeUpdate() != 1) {
                throw new DAOException("ID not found: " + id);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private Customer extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt(1);
        String name = rs.getString(2);
        String email = rs.getString(3);
        Integer age = rs.getInt(4);
        return new Customer(id, name, email, age);
    }
}
