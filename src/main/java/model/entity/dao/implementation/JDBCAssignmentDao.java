package model.entity.dao.implementation;

import model.dto.IndexDto;
import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.mappers.implementation.AssignmentMapper;
import model.entity.dao.mappers.implementation.IndexDtoMapper;
import model.exception.IllegalDeleteException;
import model.exception.UserOrCarNotFountException;
import org.apache.log4j.Logger;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCAssignmentDao implements AssignmentDao {
    private static final Logger log = Logger.getLogger(JDBCAssignmentDao.class);

    private Connection connection;

    public JDBCAssignmentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Assignment create(Assignment entity) {
        final String queryLinkId = QueryManager.getProperty("assignment.findLinkId");
        final String queryCreate = QueryManager.getProperty("assignment.create");
        int linkId;
        try(PreparedStatement preparedStatement = connection.prepareStatement(queryLinkId)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, entity.getDriver().getId());
            preparedStatement.setInt(2, entity.getBus().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
               linkId = resultSet.getInt("id");
            } else throw new UserOrCarNotFountException("admin.insert.assignment.not_found");
            try(PreparedStatement statement = connection.prepareStatement(queryCreate, Statement.RETURN_GENERATED_KEYS)) {
                AssignmentMapper mapper = new AssignmentMapper();
                mapper.extractToStatement(statement, entity, linkId);
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                        connection.commit();
                        return entity;
                    } else return null;
                }
            }
        } catch (SQLException ex){
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error("Error", ex);
                throw new RuntimeException("admin.insert.assignment.unknown");
            }
            log.error("Error", ex);
            throw new RuntimeException("admin.insert.assignment.unknown");
        }
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCountRows() {
        final String query = QueryManager.getProperty("assignment.getCountAppliedRows");
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                return resultSet.getInt("count");
            }else return 0;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getCountRowsForDriverByStatus(int driverId, Assignment.Status status) {
        final String query = QueryManager.getProperty("assignment.getCountRowsForDriver");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, driverId);
            statement.setString(2, String.valueOf(status));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("count");
            }else return 0;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getCountHistoryRows(int driverId) {
        final String query = QueryManager.getProperty("assignment.getCountHistoryRowsForDriver");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, driverId);
            statement.setString(2, String.valueOf(Assignment.Status.applied));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("count");
            }else return 0;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Assignment> findForUser(int id, int limit, int offset, Assignment.Status status) {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query = QueryManager.getProperty("assignment.findForUser");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, String.valueOf(status));
            statement.setInt(3, limit);
            statement.setInt(4, offset);
            return getAssignments(assignments, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Assignment> findPastForUser(int id, int limit, int offset) {
        List<Assignment> assignments = new CopyOnWriteArrayList<>();
        final String query = QueryManager.getProperty("assignment.findPastForUser");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
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
    public void updateToApplied(int assignmentId) {
        final String query = QueryManager.getProperty("assignment.updateToAppliedForUser");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(Assignment.Status.applied));
            preparedStatement.setInt(2, assignmentId);
            int i = preparedStatement.executeUpdate();
            if(i == 0) throw new RuntimeException("driver.button");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IndexDto> findAllFutureApplied(int limit, int offset) {
        final String query = QueryManager.getProperty("assignment.findAllFutureApplied");
        List<IndexDto> assignments = new CopyOnWriteArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            IndexDtoMapper indexDtoMapper = new IndexDtoMapper();
            while (resultSet.next()) {
                IndexDto dto = indexDtoMapper.extractFromResultSet(resultSet);
                assignments.add(dto);
            }
            return new ArrayList<>(assignments);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
