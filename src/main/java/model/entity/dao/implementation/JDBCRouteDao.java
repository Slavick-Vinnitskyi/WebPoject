package model.entity.dao.implementation;

import model.entity.Route;
import model.entity.dao.RouteDao;
import model.entity.dao.mappers.implementation.RouteMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCRouteDao implements RouteDao {

    private Connection connection;

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Route entity) {

    }

    @Override
    public Route findById(int id) {
        return null;
    }

    @Override
    public List<Route> findAll() {
        List<Route> routes = new CopyOnWriteArrayList<>();
        final String query = "select * from edited_car_park.route";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            RouteMapper routeMapper = new RouteMapper();
            while (resultSet.next()) {
                Route route = routeMapper.extractFromResultSet(resultSet);
                routes.add(route);
            }
            return new ArrayList<>(routes);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Route entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
