package model.entity.dao.mappers.implementation;

import model.entity.Car;
import model.entity.dao.mappers.ObjectMapper;
import model.entity.enums.LicenseType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CarMapper implements ObjectMapper<Car> {
    @Override
    public Car extractFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car_id"));
        car.setModel(resultSet.getString("model"));
        car.setYear(resultSet.getInt("year"));
        car.setLicenseType(LicenseType.valueOf(resultSet.getString("type")));
        return car;
    }

    @Override
    public Car makeUnique(Map<Integer, Car> cache, Car user) {
        return null;
    }
}
