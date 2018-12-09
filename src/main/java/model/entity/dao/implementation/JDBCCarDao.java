package model.entity.dao.implementation;

import model.entity.Car;
import model.entity.dao.CarDao;

import java.sql.SQLException;
import java.util.List;

public class JDBCCarDao implements CarDao {
    @Override
    public void create(Car entity) {

    }

    @Override
    public Car findById(int id) {
        return null;
    }

    @Override
    public List<Car> findAll() throws SQLException {
        return null;
    }

    @Override
    public void update(Car entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
