package model.entity.dao.implementation;

import model.entity.Car;
import model.entity.User;
import model.entity.dao.UserDao;
import model.entity.dao.mappers.implementation.CarMapper;
import model.entity.dao.mappers.implementation.UserMapper;
import model.exception.UserNotFoundException;

import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User entity) {
        final String queryInsertUser = "insert into person(login, password, first_name, second_name, license, role) VALUES (?,?,?,?,?,'driver')";
        final String querySelectCarsId = "select car_id from car where type=?";
        final String queryInsertToLinkTable = "insert into person_to_car (fk_person_id, fk_car_id) values (?,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement insertToUserTable = connection.prepareStatement(queryInsertUser, Statement.RETURN_GENERATED_KEYS);
            insertToUserTable.setString(1, entity.getLogin());
            insertToUserTable.setString(2, entity.getPassword());
            insertToUserTable.setString(3, entity.getFirstName());
            insertToUserTable.setString(4, entity.getSecondName());
            insertToUserTable.setString(5, entity.getLicenseType().toString());
            insertToUserTable.executeUpdate();
            try (ResultSet generatedKeys = insertToUserTable.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else throw new SQLException("Something wrong with user id");
            }
            PreparedStatement selectCars = connection.prepareStatement(querySelectCarsId);
            selectCars.setString(1, entity.getLicenseType().toString());
            ResultSet carsId = selectCars.executeQuery();

            PreparedStatement insertToLinkTable = connection.prepareStatement(queryInsertToLinkTable, Statement.RETURN_GENERATED_KEYS);
            while (carsId.next()) {
                insertToLinkTable.setInt(1, entity.getId());
                insertToLinkTable.setInt(2, carsId.getInt("car_id"));
                insertToLinkTable.addBatch();
            }
            insertToLinkTable.executeBatch();
            insertToLinkTable.close();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        final String query = "select * from edited_car_park.person where person_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            return getUser(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {


        List<User> drivers = new CopyOnWriteArrayList<>();

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
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public List<User> findAllCarToDriver() throws SQLException {
        Map<Integer, User> drivers = new HashMap<>();
        Map<Integer, Car> cars = new HashMap<>();

        final String query = "select * from person_to_car ptc " +
                "left join person p on ptc.fk_person_id = p.person_id " +
                "left join car c on ptc.fk_car_id = c.car_id";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();
            CarMapper carMapper = new CarMapper();

            while (rs.next()) {
                User driver = userMapper.extractFromResultSet(rs);
                Car car = carMapper.extractFromResultSet(rs);

                driver = userMapper.makeUnique(drivers, driver);
                car = carMapper.makeUnique(cars, car);
                driver.getCars().add(car);
            }
            return new ArrayList<>(drivers.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}