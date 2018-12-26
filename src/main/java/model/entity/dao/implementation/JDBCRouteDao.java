package model.entity.dao.implementation;

import model.entity.Assignment;
import model.entity.Route;
import model.entity.dao.RouteDao;
import model.entity.dao.mappers.implementation.AssignmentMapper;
import model.entity.dao.mappers.implementation.RouteMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class JDBCRouteDao implements RouteDao {

    private Connection connection;

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Route entity) {
        final String query = "insert into route (start, finish, start_ua, finish_ua) VALUES (?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){

        RouteMapper mapper = new RouteMapper();
        PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity);
        preparedStatement.executeUpdate();


        }catch (SQLException ex){

        }

    }

    @Override
    public Route findById(int id) {
        final String query = "select * from route " +
                "where route.route_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Route route = new RouteMapper().extractFromResultSet(resultSet);
                return route;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
