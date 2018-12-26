package model.entity.dao.mappers.implementation;

import model.entity.Route;
import model.entity.dao.mappers.ObjectMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("route_id"));
        route.setStart(resultSet.getString("start"));
        route.setFinish(resultSet.getString("finish"));
        route.setStartUa(resultSet.getString("start_ua"));
        route.setFinishUa(resultSet.getString("finish_ua"));
        return route;
    }

    public PreparedStatement extractToStatement(PreparedStatement statement, Route route) throws SQLException {
        statement.setString(1, route.getStart());
        statement.setString(2, route.getFinish());
        statement.setString(3, route.getStartUa());
        statement.setString(4, route.getFinishUa());
        return statement;
    }

    @Override
    public Route makeUnique(Map<Integer, Route> cache, Route user) {
        return null;
    }
}
