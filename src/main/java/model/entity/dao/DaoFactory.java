package model.entity.dao;

import model.entity.dao.implementation.JDBCDaoFactory;

import java.sql.SQLException;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao() throws SQLException, ClassNotFoundException;
    public abstract RouteDao createRouteDao() throws SQLException, ClassNotFoundException;
    public abstract AssignmentDao createAssignmentDao() throws SQLException, ClassNotFoundException;
    public abstract AssignmentDao createCarDao() throws SQLException, ClassNotFoundException;

    public static DaoFactory getInstance() throws Exception {
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
