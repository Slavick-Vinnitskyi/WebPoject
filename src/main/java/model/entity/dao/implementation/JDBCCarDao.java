package model.entity.dao.implementation;

import model.entity.Car;
import model.entity.dao.CarDao;
import model.entity.dao.mappers.implementation.CarMapper;

import java.sql.*;
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
        final String queryInsertCar = "insert into car (model, year, type) values (?,?,?)";
        final String querySelectDriverIds = "select person_id from person where license = ?";
        final String queryInsertToLinkTable = "insert into person_to_car(fk_person_id, fk_car_id) VALUES (?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertCarToCarTable = connection.prepareStatement(queryInsertCar, Statement.RETURN_GENERATED_KEYS);
            insertCarToCarTable.setString(1, entity.getModel());
            insertCarToCarTable.setDate(2, Date.valueOf(entity.getYear()));
            insertCarToCarTable.setString(3, entity.getLicenseType().toString());
            insertCarToCarTable.executeUpdate();
            try (ResultSet generatedKeys = insertCarToCarTable.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Smt wrong with id");
            }

            PreparedStatement selectDrivers = connection.prepareStatement(querySelectDriverIds);
            selectDrivers.setString(1, entity.getLicenseType().toString());
            ResultSet driversId = selectDrivers.executeQuery();

            PreparedStatement insertToLinkTable = connection.prepareStatement(queryInsertToLinkTable);
            while (driversId.next()) {
                insertToLinkTable.setInt(1, driversId.getInt("person_id"));
                insertToLinkTable.setInt(2, entity.getId());
                insertToLinkTable.addBatch();
            }
            insertToLinkTable.executeBatch();
            insertToLinkTable.close();
            connection.commit();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
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
