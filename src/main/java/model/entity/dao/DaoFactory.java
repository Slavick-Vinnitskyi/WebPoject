package model.entity.dao;

import model.entity.dao.implementation.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract RouteDao createRouteDao();
    public abstract AssignmentDao createAssignmentDao();
    public abstract CarDao createCarDao();

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
