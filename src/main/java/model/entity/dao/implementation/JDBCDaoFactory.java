package model.entity.dao.implementation;

import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;
import model.entity.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TODO : connection pool does not work on THIS PC

/**
 * @see ConnectionPoolHolder - doesn`t work
 * */
public class JDBCDaoFactory extends DaoFactory {
//    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/edited_car_park?useSSL=false";
    private final static String DATABASE_USER = "root";
    private final static String DATABASE_PASSWORD = "root";

    /*public JDBCDaoFactory() throws Exception {
    }*/

    @Override
    public UserDao createUserDao() throws SQLException, ClassNotFoundException {

        return new JDBCUserDao(getConnection());
    }

    @Override
    public RouteDao createRouteDao() throws SQLException, ClassNotFoundException {
        return new JDBCRouteDao(getConnection());
    }

    @Override
    public AssignmentDao createAssignmentDao() throws SQLException, ClassNotFoundException {
        return new JDBCAssignmentDao(getConnection());
    }

    @Override
    public AssignmentDao createCarDao() throws SQLException, ClassNotFoundException {
        return null;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
//        return dataSource.getConnection();
        Class.forName(DRIVER);
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
