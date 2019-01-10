package model.entity.dao.implementation;

import model.entity.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * */
public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    /**
     * @return JDBC implementation of UserDao
     */
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public RouteDao createRouteDao() {
        return new JDBCRouteDao(getConnection());
    }

    @Override
    public AssignmentDao createAssignmentDao() {
        return new JDBCAssignmentDao(getConnection());
    }

    @Override
    public CarDao createCarDao() {
        return new JDBCCarDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
