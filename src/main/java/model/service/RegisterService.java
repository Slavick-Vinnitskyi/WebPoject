package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;
import model.exception.UserAlreadyExistException;

import java.sql.SQLException;


public class RegisterService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param login to find if this user already exist
     * @return true if this user already exist
     */
    public boolean isLoginExist(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {
            User byName = dao.findByName(login);
            return byName.getLogin() != null;
        } catch (UserAlreadyExistException ex) {
            return false;
        }
    }

    /**
     * @param user which want to register
     * @return user if was registered successfully or
     * @throws
     */
    public User registerNewUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }
}