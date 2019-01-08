package model.entity.dao;

import model.entity.dao.implementation.JDBCDaoFactory;

import java.sql.SQLException;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao() throws SQLException;
    public abstract RouteDao createRouteDao() throws SQLException;
    public abstract AssignmentDao createAssignmentDao() throws SQLException;
    public abstract CarDao createCarDao() throws SQLException;

    public static DaoFactory getInstance() {
        if(daoFactory == null) {
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
