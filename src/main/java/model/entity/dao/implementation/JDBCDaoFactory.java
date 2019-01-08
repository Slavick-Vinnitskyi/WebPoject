package model.entity.dao.implementation;

import model.entity.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TODO : connection pool does not work on THIS PC

/**
 * @see ConnectionPoolHolder - doesn`t work
 * */
public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    /**
     * @return JDBC implementation of UserDao
     * @throws SQLException if something wrong with driver
     */
    @Override
    public UserDao createUserDao() throws SQLException {

        return new JDBCUserDao(getConnection());
    }

    @Override
    public RouteDao createRouteDao() throws SQLException {
        return new JDBCRouteDao(getConnection());
    }

    @Override
    public AssignmentDao createAssignmentDao() throws SQLException {
        return new JDBCAssignmentDao(getConnection());
    }

    @Override
    public CarDao createCarDao() throws SQLException {
        return new JDBCCarDao(getConnection());
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
