package model.entity.dao;

import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    User create (T entity) throws SQLException;
    T findById(int id);
    List<T> findAll() throws SQLException;
    void update(T entity);
    void delete(int id);
    void close();
}
