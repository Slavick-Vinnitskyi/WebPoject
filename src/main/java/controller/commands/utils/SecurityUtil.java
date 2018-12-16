package controller.commands.utils;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;
import model.entity.dao.implementation.UserNotFoundException;

import java.sql.SQLException;

/**
 * Возвращает нужные для авторизации и аутентификации данные
 */
public class SecurityUtil {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public SecurityUtil() throws Exception {
    }

    public User validateUser(String login, String password) throws SQLException, ClassNotFoundException, UserNotFoundException {
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.findByLoginAndPassword(login, password);
        }
    }

}
