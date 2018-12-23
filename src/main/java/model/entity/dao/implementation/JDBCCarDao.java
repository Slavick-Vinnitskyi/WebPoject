package model.entity.dao.implementation;

import model.entity.Car;
import model.entity.Route;
import model.entity.dao.CarDao;
import model.entity.dao.mappers.implementation.CarMapper;
import model.entity.dao.mappers.implementation.RouteMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCCarDao implements CarDao {
    private Connection connection;

    public JDBCCarDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Car entity) {

    }

    @Override
    public Car findById(int id) {
        return null;
    }

    @Override
    public List<Car> findAll() throws SQLException {
        List<Car> cars = new CopyOnWriteArrayList<>();
        final String query = "select * from edited_car_park.car";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            CarMapper carMapper = new CarMapper();
            while (resultSet.next()) {
                Car car = carMapper.extractFromResultSet(resultSet);
                cars.add(car);
            }
            return new ArrayList<>(cars);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
