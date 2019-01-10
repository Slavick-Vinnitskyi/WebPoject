package model.entity.dao.implementation;

import model.dto.IndexDto;
import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.mappers.implementation.AssignmentMapper;
import model.entity.dao.mappers.implementation.IndexDtoMapper;
import model.exception.IllegalDeleteException;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCAssignmentDao implements AssignmentDao {

    private Connection connection;

    public JDBCAssignmentDao(Connection connection) {
        this.connection = connection;
    }


    public void create(Assignment entity, int linkId) throws SQLException {
        final String query = QueryManager.getProperty("assignment.create");
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            AssignmentMapper mapper = new AssignmentMapper();
            PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity, linkId);
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public Assignment create(Assignment entity) throws SQLException {

        return null;
    }

    @Override
    public Assignment findById(int id) {
        final String query = QueryManager.getProperty("assignment.findById");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    return new AssignmentMapper().extractAssignmentWithRoute(resultSet);
                }
            } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<Assignment> findAll() {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query = QueryManager.getProperty("assignment.findAll");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return getAssignments(assignments, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Assignment> findForUser(int id, Assignment.Status status) {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query = QueryManager.getProperty("assignment.findForUser");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, String.valueOf(status));
            return getAssignments(assignments, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Assignment> findPastForUser(int id) {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query = QueryManager.getProperty("assignment.findPastForUser");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return getAssignments(assignments, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(Assignment entity) {
        final String query = QueryManager.getProperty("assignment.update");
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1 , String.valueOf(entity.getStatus()));
            preparedStatement.setInt(2 , entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateToAppliedForUser(Assignment entity) {
        final String query = QueryManager.getProperty("assignment.updateToAppliedForUser");
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1 , String.valueOf(entity.getStatus()));
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IndexDto> findAllFutureApplied() {
        final String query = QueryManager.getProperty("assignment.findAllFutureApplied");
        List<IndexDto> assignments = new CopyOnWriteArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            IndexDtoMapper indexDtoMapper = new IndexDtoMapper();
            while (resultSet.next()) {
                IndexDto dto = indexDtoMapper.extractFromResultSet(resultSet);
                assignments.add(dto);
            }
            return new ArrayList<>(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Assignment> findByStatus(Assignment.Status status) {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query= QueryManager.getProperty("assignment.findByStatus");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, String.valueOf(status));
            return getAssignments(assignments, statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Assignment> findAllByDate(Date date) {
        final String query = QueryManager.getProperty("assignment.findAllByDate");
        List<Assignment> assignments = new CopyOnWriteArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            AssignmentMapper mapper = new AssignmentMapper();
            while (resultSet.next()) {
                Assignment dto = mapper.extractAssignmentWithPerson(resultSet);
                assignments.add(dto);
            }
            return new ArrayList<>(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int findLinkId(int driverId, int carId) {
        final String query = QueryManager.getProperty("assignment.findLinkId");
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1 , driverId);
            preparedStatement.setInt(2, carId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("id");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private List<Assignment> getAssignments(List<Assignment> assignments, PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return getAssignments(assignments, resultSet);
    }

    private List<Assignment> getAssignments(List<Assignment> assignments, ResultSet resultSet) throws SQLException {
        AssignmentMapper assignmentMapper = new AssignmentMapper();
        while (resultSet.next()) {
            Assignment assignment = assignmentMapper.extractFromResultSet(resultSet);
            assignments.add(assignment);
        }
        return new ArrayList<>(assignments);
    }

    @Override
    public void delete(int id) {
        final String query = "delete from assignment where assignment_id = ? and status = 'assigned'";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setInt(1, id);
            int countDeletedRows = statement.executeUpdate();
            connection.commit();
            if(countDeletedRows == 0) throw new IllegalDeleteException("admin.delete.assignment");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
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
