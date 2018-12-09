package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;

import java.sql.SQLException;

public class DriverAboutService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public DriverAboutService() throws Exception {
    }

    public User getInfoAboutUser(int id) throws SQLException, ClassNotFoundException {
        try(UserDao dao = daoFactory.createUserDao()) {
                return dao.findById(id);
        }
    }

}
