package model.entity.dao.implementation;

import model.dto.IndexDto;
import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.mappers.implementation.AssignmentMapper;
import model.entity.dao.mappers.implementation.IndexDtoMapper;

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
        final String query = "insert into assignment (date_start, status, route_id, person_to_car_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            AssignmentMapper mapper = new AssignmentMapper();
            PreparedStatement preparedStatement = mapper.extractToStatement(statement, entity, linkId);
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void create(Assignment entity) throws SQLException {

    }

    @Override
    public Assignment findById(int id) {
        final String query = "select * from assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "where assignment.assignment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Assignment assignment = new AssignmentMapper().extractAssignmentWithRoute(resultSet);
                    return assignment;
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

        final String query = "select * from assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join car c on ptc.fk_car_id = c.car_id " +
                "left join person p on ptc.fk_person_id = p.person_id where date_start > DATE(NOW())";
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
        final String query = "select * " +
                "from assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join car c on ptc.fk_car_id = c.car_id " +
                "left join person p on ptc.fk_person_id = p.person_id " +
                "where p.person_id = ? and assignment.status = ?";
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
        final String query = "select * " +
                "from assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join car c on ptc.fk_car_id = c.car_id " +
                "left join person p on ptc.fk_person_id = p.person_id " +
                "where p.person_id = ? and date_start > DATE(NOW())";
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
        final String query = "UPDATE assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join car c on ptc.fk_car_id = c.car_id " +
                "left join person p on ptc.fk_person_id = p.person_id " +
                "SET status = ? where p.person_id = ?";
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
        final String query = "UPDATE assignment " +
        "left join route r on assignment.route_id = r.route_id " +
                "SET status = ? where assignment.assignment_id = ?";
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
                final String query = "select * from edited_car_park.assignment" +
                " left join route on edited_car_park.assignment.route_id = route.route_id" +
                " where date_start >= DATE(NOW()) and status='applied'" +
                        "ORDER BY date_start";
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
        final String query = "select * " +
                "from assignment " +
                "left join route r on assignment.route_id = r.route_id " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join car c on ptc.fk_car_id = c.car_id " +
                "left join person p on ptc.fk_person_id = p.person_id " +
                "where assignment.status = ?";
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
        final String query = "select * from assignment " +
                "left join person_to_car ptc on assignment.person_to_car_id = ptc.id " +
                "left join person on ptc.fk_person_id = person.person_id " +
                "where date_start = ? and status='applied'";
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
        final String query = "select id from person_to_car where fk_person_id = ? and fk_car_id  = ?";
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

    }

    @Override
    public void close() {

    }
}
