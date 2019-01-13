package model.entity.dao;

import config.DBConfigurer;
import model.entity.dao.implementation.JDBCAssignmentDao;
import model.entity.dao.implementation.JDBCCarDao;
import model.entity.dao.implementation.JDBCRouteDao;
import model.entity.dao.implementation.JDBCUserDao;

import java.sql.Connection;

public class DaoFactoryTest extends DaoFactory {

    private DBConfigurer dbRunner;

    public DaoFactoryTest(DBConfigurer dbRunner) {
        this.dbRunner = dbRunner;
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
    @Override
    public RouteDao createRouteDao() {
        return new JDBCRouteDao(getConnection());
    }

    @Override
    public CarDao createCarDao() {
        return new JDBCCarDao(getConnection());
    }

    @Override
    public AssignmentDao createAssignmentDao() {
        return new JDBCAssignmentDao(getConnection());
    }

    private Connection getConnection() {
        return dbRunner.getConnection();
    }
}