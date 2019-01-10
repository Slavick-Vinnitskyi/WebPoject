package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;

import java.sql.SQLException;

/**
 * Будет :
 *  валидировать данные на коректность
 *  проверять есть ли уже такой логин *
 */
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
        }
    }

    public User registerNewUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user);
        }
    }
}