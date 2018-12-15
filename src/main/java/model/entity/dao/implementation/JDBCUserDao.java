package model.entity.dao.implementation;

import model.entity.User;
import model.entity.dao.UserDao;
import model.entity.dao.mappers.implementation.UserMapper;

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

        User findingUser = new User();
        final String query = "select * from edited_car_park.person where person_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(0, id);
            return getUser(findingUser, st);

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

        User findingUser = new User();
        final String query = "select * from edited_car_park.person where login = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, login);
            return getUser(findingUser, st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUser(User findingUser, PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        UserMapper driverMapper = new UserMapper();

        while (rs.next()) {
            findingUser = driverMapper
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