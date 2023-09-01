package jdbc.dao.core;

import java.util.List;
import java.util.Optional;

public abstract class DAO<T, ID> {

    protected final DBConn conn;

    public DAO(DBConn conn) {
        this.conn = conn;
    }

    public Optional<T> findById(ID id) {
        throw new UnsupportedOperationException();
    }

    public List<T> list() {
        throw new UnsupportedOperationException();
    }

    public ID insert(T ojb) {
        throw new UnsupportedOperationException();
    }

    public void deleteById(ID id) {
        throw new UnsupportedOperationException();
    }

    public void update(T obj) {
        throw new UnsupportedOperationException();
    }

    public List<T> select(String sql) {
        throw new UnsupportedOperationException();
    }
}
