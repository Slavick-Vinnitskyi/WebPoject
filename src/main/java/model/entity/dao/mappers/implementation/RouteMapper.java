package model.entity.dao.mappers.implementation;

import model.dto.LocalizedRoute;
import model.entity.Route;
import model.entity.dao.mappers.ObjectMapper;
import util.LocaleManager;
import util.ThreadLocalWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {
    @Override
    public Route extractFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("route_id"));
        route.setStart(resultSet.getString(LocaleManager.getProperty("route.start", ThreadLocalWrapper.getLocale().getLanguage())));
        route.setFinish(resultSet.getString(LocaleManager.getProperty("route.end", ThreadLocalWrapper.getLocale().getLanguage())));
        return route;
    }

    public PreparedStatement extractToStatement(PreparedStatement statement, Route route) throws SQLException {
        statement.setString(1, route.getStart());
        statement.setString(2, route.getFinish());
        return statement;
    }

    public PreparedStatement extractToStatement(PreparedStatement statement, LocalizedRoute route) throws SQLException {
        LocalizedRoute.LocalizedPart localizedPart = route.getLocalizedPartMap().get("ua");
        statement.setString(1, route.getRoute().getStart());
        statement.setString(2, route.getRoute().getFinish());
        statement.setString(3, localizedPart.getStart());
        statement.setString(4, localizedPart.getFinish());
        return statement;
    }

    @Override
    public Route makeUnique(Map<Integer, Route> cache, Route user) {
        return null;
    }
}
