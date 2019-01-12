package model.entity.dao.mappers.implementation;

import model.entity.Assignment;
import model.entity.Car;
import model.entity.Route;
import model.entity.User;
import model.entity.dao.mappers.ObjectMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AssignmentMapper implements ObjectMapper<Assignment> {


    public PreparedStatement extractToStatement(PreparedStatement statement, Assignment assignment, int linkId) throws SQLException {
        statement.setDate(1, Date.valueOf(assignment.getDate()));
        statement.setString(2, String.valueOf(assignment.getStatus()));
        statement.setInt(3, assignment.getRoute().getId());
        statement.setInt(4, linkId);
        return statement;
    }
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

    public Assignment extractAssignmentWithRoute(ResultSet resultSet) throws SQLException {

        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getInt("assignment_id"));
        assignment.setDate((resultSet.getDate("date_start").toLocalDate()));
        assignment.setStatus(Assignment.Status.valueOf(resultSet.getString("status")));
        Route route = new RouteMapper().extractFromResultSet(resultSet);
        assignment.setRoute(route);
        return assignment;
    }
    public Assignment extractAssignmentWithPerson(ResultSet resultSet) throws SQLException {

        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getInt("assignment_id"));
        assignment.setDate((resultSet.getDate("date_start").toLocalDate()));
        assignment.setStatus(Assignment.Status.valueOf(resultSet.getString("status")));
        User user = new UserMapper().extractFromResultSet(resultSet);
        assignment.setDriver(user);
        return assignment;
    }

    public Assignment extractAssignmentWithPersonAndCar(ResultSet resultSet) throws SQLException {

        Assignment assignment = extractAssignmentWithPerson(resultSet);
        User user = new UserMapper().extractFromResultSet(resultSet);
        Car bus = new CarMapper().extractFromResultSet(resultSet);
        assignment.setDriver(user);
        assignment.setBus(bus);
        return assignment;
    }




    @Override
    public Assignment makeUnique(Map<Integer, Assignment> cache, Assignment user) {
        return null;
    }
}
