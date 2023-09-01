package jdbc.dao.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class DBConn implements AutoCloseable {

    private final Connection conn;

    public DBConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement statement(String sql) {
        try {
            return conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DAOException("Error closing connection", e);
        }
    }

    public void beginTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void commitTransaction() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void rollbackTransaction() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
