package model.entity.dao.implementation;

import model.dto.LocalizedRoute;
import model.entity.Route;
import model.entity.dao.RouteDao;
import model.entity.dao.mappers.implementation.RouteMapper;
import org.apache.log4j.Logger;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCRouteDao implements RouteDao {

    private static final Logger log = Logger.getLogger(JDBCRouteDao.class);
    private Connection connection;

    public JDBCRouteDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Route create(Route entity) {
        final String query = QueryManager.getProperty("route.create");
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            RouteMapper mapper = new RouteMapper();
            PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity);
            preparedStatement.executeUpdate();
            log.info(entity + " was inserted to DB");

        } catch (SQLException ex) {
            log.error(ex);
            throw new RuntimeException(ex);
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
            log.info(route + " localized was inserted to DB");

        } catch (SQLException ex) {
            log.error(ex);
            throw new RuntimeException(ex);
        }
        return route;
    }

    @Override
    public Route findById(int id) {
        final String query = QueryManager.getProperty("route.findById");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new RouteMapper().extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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
            log.info(routes + " was selected from DB");
            return new ArrayList<>(routes);
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Route entity) {
        try (PreparedStatement statement = connection.prepareStatement(QueryManager.getProperty("route.update"))) {
            RouteMapper mapper = new RouteMapper();
            PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity);
            preparedStatement.executeUpdate();
            log.info("Route was updated to " + entity);
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

        try (PreparedStatement statement = connection.prepareStatement(QueryManager.getProperty("route.delete"))) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
