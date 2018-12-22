package model.entity.dao.implementation;

import model.entity.User;
import model.entity.dao.UserDao;
import model.entity.dao.mappers.implementation.UserMapper;
import model.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(int id) {
        final String query = "select * from edited_car_park.person where person_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(0, id);
            return getUser(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public List<User> findAll() {


        List <User> drivers = new CopyOnWriteArrayList<>();

        final String query = "select * from edited_car_park.person where role = Driver";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper driverMapper = new UserMapper();

            while (rs.next()) {
                User user = driverMapper
                        .extractFromResultSet(rs);
                drivers.add(user);
            }
            return new ArrayList<>(drivers);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findByName(String login) {
        final String query = "select * from edited_car_park.person where login = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            return getUser(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws UserNotFoundException, SQLException {
        final String query = "select * from edited_car_park.person where login = ? and password = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return getUser(st);
            } else {
                throw new UserNotFoundException("Wrong login or password");
            }
        }
    }

    private User getUser(PreparedStatement st) throws SQLException {
        User findingUser = new User();
        ResultSet rs = st.executeQuery();
        UserMapper userMapper = new UserMapper();

        while (rs.next()) {
            findingUser = userMapper
                    .extractFromResultSet(rs);
        }
        return findingUser;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}