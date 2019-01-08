package model.entity.dao.implementation;

import model.dto.LocalizedRoute;
import model.entity.Route;
import model.entity.User;
import model.entity.dao.RouteDao;
import model.entity.dao.mappers.implementation.RouteMapper;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCRouteDao implements RouteDao {

    private Connection connection;

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Route create(Route entity) {
        final String query = QueryManager.getProperty("route.create");
        try(PreparedStatement statement = connection.prepareStatement(query)){

        RouteMapper mapper = new RouteMapper();
        PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity);
        preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    @Override
    public LocalizedRoute createLocalizedRoute(LocalizedRoute route) {
        final String query = QueryManager.getProperty("route.create");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            RouteMapper mapper = new RouteMapper();
            PreparedStatement preparedStatement = mapper.extractToStatement(statement, route);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return route;
    }

    @Override
    public Route findById(int id) {
        final String query = QueryManager.getProperty("route.findById");
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
        final String query = QueryManager.getProperty("route.findAll");
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
