package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;
import model.entity.dao.implementation.UserNotFoundException;

import java.sql.SQLException;

/**
 * Будет валидировать введенные данные на коректность
 */
public class LoginService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public LoginService() throws Exception {
    }

    /**
     * @param login user login
     * @param password user password
     * @return User if he exist in database
     * @throws SQLException if something wrong with connection
     * @throws ClassNotFoundException when CL can`t find implemented 'createUserDao' method
     * @throws UserNotFoundException when user does`nt exist in database
     */
    public User validateUser(String login, String password) throws SQLException, ClassNotFoundException, UserNotFoundException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findByLoginAndPassword(login, password);
        }
    }

}
