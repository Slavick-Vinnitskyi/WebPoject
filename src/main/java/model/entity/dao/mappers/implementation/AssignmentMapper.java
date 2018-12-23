package model.entity.dao.mappers.implementation;

import model.entity.Assignment;
import model.entity.Car;
import model.entity.Route;
import model.entity.User;
import model.entity.dao.mappers.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AssignmentMapper implements ObjectMapper<Assignment> {


    public Assignment extractFromResultSet(ResultSet resultSet) throws SQLException {

        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getInt("assignment_id"));
        assignment.setDate((resultSet.getDate("date_start").toLocalDate()));
        assignment.setStatus(Assignment.Status.valueOf(resultSet.getString("status")));
        Route route = new RouteMapper().extractFromResultSet(resultSet);
        assignment.setRoute(route);
        User user = new UserMapper().extractFromResultSet(resultSet);
        Car bus = new CarMapper().extractFromResultSet(resultSet);
        assignment.setDriver(user);
        assignment.setBus(bus);
        return assignment;
    }

    public Assignment extractFromResultSet1(ResultSet resultSet) throws SQLException {

        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getInt("assignment_id"));
        assignment.setDate((resultSet.getDate("date_start").toLocalDate()));
        assignment.setStatus(Assignment.Status.valueOf(resultSet.getString("status")));
        Route route = new RouteMapper().extractFromResultSet(resultSet);
        assignment.setRoute(route);
        return assignment;
    }




    @Override
    public Assignment makeUnique(Map<Integer, Assignment> cache, Assignment user) {
        return null;
    }
}
