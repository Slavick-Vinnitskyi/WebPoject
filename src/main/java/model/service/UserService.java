package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<User> getAllUser() throws SQLException, ClassNotFoundException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User getUser(String name) throws SQLException, ClassNotFoundException {
        return daoFactory.createUserDao().findByName(name);
    }
}